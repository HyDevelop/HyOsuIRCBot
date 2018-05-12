package cc.moecraft.scripts;

import cc.moecraft.irc.osubot.osu.OsuHtmlUtils;
import cc.moecraft.irc.osubot.osu.data.AchievementData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.UnexpectedHtmlJsonException;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.FileUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.irc.osubot.utils.StringUtils;
import cc.moecraft.logger.DebugLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 此类由 Hykilpikonna 在 2018/05/10 创建!
 * Created by Hykilpikonna on 2018/05/10!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementClassGenerator
{
    private static DebugLogger logger = new DebugLogger("AchievementClassGenerator", true);

    private static String generatePath = "./Generated/";
    private static String formatPath = "./src/test/resources/AchievementGeneratorFormat.java";
    private static String baseClassPackage = "cc.moecraft.irc.osubot.achievement.achievements";
    private static Pattern regexForFindingBeatmap = Pattern.compile("(?<=\"https://osu.ppy.sh/b/)(.*)(?=\")");
    private static Pattern regexForFindingMods = Pattern.compile("(?<=with)(.*)(?=mods)");

    public static void main(String[] args) throws Exception {
        logger.log("开始执行");

        OsuHtmlUtils osuHtmlUtils = new OsuHtmlUtils(new DownloadUtils(5000));

        ArrayList<AchievementData> data = osuHtmlUtils.getAllAvailableAchievements();

        String format = FileUtils.readFileAsString(new File(formatPath));

        int count = 0;

        for (AchievementData achievement : data)
        {
            count ++;

            logger.log("正在处理: " + achievement.getName());

            String noSpaceName = getNoSpaceName(achievement.getName())
                    .replaceAll("[^A-Za-z0-9 ]", "")
                    .replace(",", "");
            String haitaiName = achievement.getName().toLowerCase()
                    .replaceAll("[^A-Za-z0-9 ]", "")
                    .replace(" ", "-");
            HashMap<String, String> haitaiMap = getHaitaiData(osuHtmlUtils, haitaiName, new ArrayList<>(Collections.singletonList("<div id=\"solution\">")));
            String haitaiData;

            if (haitaiMap == null || haitaiMap.get("<div id=\"solution\">") == null)
            {
                haitaiData = "无教程信息";
                logger.log("  - 该成就无教程信息 (http://haitai.jp/" + haitaiName + "/)");
            }
            else
            {
                haitaiData = haitaiMap.get("<div id=\"solution\">");
            }

            AchievementGenerateData generateData = AchievementGenerateData.builder()
                    .class_package(baseClassPackage + ".Achievement" + noSpaceName)
                    .current_date_and_time(new SimpleDateFormat("yyyy/MM/DD HH:mm:ss").format(new Date()))
                    .name_capitalized(noSpaceName)
                    .id(String.valueOf(achievement.getId()))
                    .name(achievement.getName())
                    .grouping(achievement.getGrouping())
                    .ordering(String.valueOf(achievement.getOrdering()))
                    .slug(achievement.getSlug())
                    .description(achievement.getDescription())
                    .mode(achievement.getMode())
                    .tutorial(haitaiData.replace("\n", " ").replace("\"", "\\\""))
                    .mods(getMods(haitaiData))
                    .recommend(haitaiData.contains("https://osu.ppy.sh/b/") ? regexForFindingBeatmap.matcher(haitaiData).matches() ? regexForFindingBeatmap.matcher(haitaiData).group() : "null" : "null")
                    .completion_time("\"未知\"")
                    .average_retry("\"未知\"")
                    .build();

            String fileContent = ReflectUtils.replaceReflectVariables(generateData, format, false, false);

            File targetFile = new File(generatePath + generateData.getClass_package().replace(".", "/") + ".java");

            createFileOrOverride(targetFile);

            FileWriter fileWriter = new FileWriter(targetFile);
            fileWriter.write(fileContent);
            fileWriter.close();

            logger.log("完成度: " + count + "/" + data.size() + " (" + Math.round((double) count / (double) data.size() * 10000d) / 100d + "%)");
            logger.log("----------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static String getNoSpaceName(String name)
    {
        return StringUtils.capitalizeFirstLetterOfEachWord(name.replace("-", " ")).replace(" ", "");
    }

    public static HashMap<String, String> getHaitaiData(OsuHtmlUtils htmlUtils, String achievementName, ArrayList<String> tags) throws MalformedURLException
    {
        HashMap<String, String> result = new HashMap<>();

        String html = htmlUtils.getDownloader().downloadAsString(new URL("http://haitai.jp/" + achievementName + "/"));

        if (html == null) return null;

        html = html.replace("</br>\n", "");

        Scanner scanner = new Scanner(html);

        while (scanner.hasNext())
        {
            String line = scanner.nextLine();

            for (String tag : tags)
            {
                if (!line.contains(tag)) continue;

                StringBuilder builder = new StringBuilder();

                while (scanner.hasNext())
                {
                    String nextLine = scanner.nextLine();

                    if (nextLine.contains("</div>")) break;

                    if (!builder.toString().equals("")) builder.append("\n");
                    builder.append(nextLine);
                }

                result.put(tag, builder.toString());
            }
        }

        return result;
    }

    private static String getMods(String haitaiData)
    {
        if (haitaiData == null) return "null";

        haitaiData = haitaiData.replace("+", " ").replace(" ", "");

        if (!regexForFindingMods.matcher(haitaiData).matches()) return "null"; // TODO: 改到格式里

        return "Mods.parseFromShortString(" + regexForFindingMods.matcher(haitaiData).group() + ")";
    }

    private static void createFileOrOverride(File file) throws IOException
    {
        if (!cc.moecraft.yaml.utils.FileUtils.createFile(file))
        {
            logger.log("文件已存在, 正在覆盖");

            file.delete();
            createFileOrOverride(file);
        }
    }
}
