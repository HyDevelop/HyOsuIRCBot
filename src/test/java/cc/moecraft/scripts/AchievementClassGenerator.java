package cc.moecraft.scripts;

import cc.moecraft.irc.osubot.osu.OsuHtmlUtils;
import cc.moecraft.irc.osubot.osu.data.AchievementData;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.FileUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.irc.osubot.utils.StringUtils;
import cc.moecraft.logger.DebugLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    private static String generatePath = "./src/main/java/";
    private static String formatPath = "./src/test/resources/AchievementGeneratorFormat.java";
    private static String baseClassPackage = "cc.moecraft.irc.osubot.achievement.achievements";
    private static Pattern regexForFindingBeatmap = Pattern.compile("(?<=\"https://osu.ppy.sh/b/)(.*)(?=\")");
    private static Pattern regexForFindingMods = Pattern.compile("(?<=with)(.*)(?=mods)");
    private static Map<String, URL> haitaiUrlMap = new HashMap<>();

    public static void main(String[] args) throws Exception 
    {
        logger.log("开始执行");
        
        setupHaitaiMap();

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
            String haitaiData = getHaitaiData(achievement, osuHtmlUtils);

            switch (achievement.getMode())
            {
                case "osu":
                    achievement.setMode("0");
                case "taiko":
                    achievement.setMode("1");
                case "fruits":
                    achievement.setMode("2");
                case "mania":
                    achievement.setMode("3");
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
                    .description(achievement.getDescription().replace("<br>", ""))
                    .mode(achievement.getMode())
                    .tutorial(haitaiData.replace("\n", " ").replace("\"", "\\\""))
                    .mods(getMods(haitaiData))
                    .recommend(haitaiData.contains("https://osu.ppy.sh/b/") ? regexForFindingBeatmap.matcher(haitaiData).matches() ? regexForFindingBeatmap.matcher(haitaiData).group() : "null" : "null")
                    .completion_time("未知")
                    .average_retry("未知")
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

    private static String getHaitaiData(AchievementData achievement, OsuHtmlUtils osuHtmlUtils) throws MalformedURLException
    {
        return getHaitaiData(achievement, osuHtmlUtils, false);
    }

    private static String getHaitaiData(AchievementData achievement, OsuHtmlUtils osuHtmlUtils, boolean retry) throws MalformedURLException
    {
        String haitaiData;
        if (haitaiUrlMap.containsKey(achievement.getName()))
        {
            HashMap<String, String> haitaiMap = getHaitaiData(osuHtmlUtils, haitaiUrlMap.get(achievement.getName()), new ArrayList<>(Collections.singletonList("<div id=\"solution\">")));

            if (haitaiMap == null)
            {
                haitaiData = "无教程信息";
                if (!retry) return getHaitaiData(achievement, osuHtmlUtils, true);
                logger.log("  - 教程信息获取为null: " + haitaiUrlMap.get(achievement.getName()));
            }
            else
            {
                haitaiData = haitaiMap.get("<div id=\"solution\">");
                logger.log("  - 教程信息链接: " + haitaiUrlMap.get(achievement.getName()));
            }
        }
        else
        {
            haitaiData = "无教程信息";
            logger.log("  - 该成就无教程信息: " + achievement.getName());
        }

        return haitaiData;
    }

    public static String getNoSpaceName(String name)
    {
        return StringUtils.capitalizeFirstLetterOfEachWord(name.replace("-", " ")).replace(" ", "");
    }

    public static HashMap<String, String> getHaitaiData(OsuHtmlUtils htmlUtils, URL url, ArrayList<String> tags) throws MalformedURLException
    {
        HashMap<String, String> result = new HashMap<>();

        String html = htmlUtils.getDownloader().downloadAsString(url);

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
        if (haitaiData == null) return "";

        haitaiData = haitaiData.replace("+", " ").replace(" ", "");

        if (!regexForFindingMods.matcher(haitaiData).matches()) return "";

        return regexForFindingMods.matcher(haitaiData).group();
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

    private static void setupHaitaiMap() throws MalformedURLException
    {
        haitaiUrlMap.put("Don't let the bunny distract you!", new URL("http://haitai.jp/dont-let-the-bunny-distract-you"));
        haitaiUrlMap.put("S-Ranker", new URL("http://haitai.jp/s-ranker"));
        haitaiUrlMap.put("Most Improved", new URL("http://haitai.jp/most-improved"));
        haitaiUrlMap.put("Non-stop Dancer", new URL("http://haitai.jp/non-stop-dancer"));
        haitaiUrlMap.put("Consolation Prize", new URL("http://haitai.jp/consolation-prize"));
        haitaiUrlMap.put("Challenge Accepted", new URL("http://haitai.jp/challenge-accepted"));
        haitaiUrlMap.put("Stumbler", new URL("http://haitai.jp/stumbler"));
        haitaiUrlMap.put("Jackpot", new URL("http://haitai.jp/jackpot"));
        haitaiUrlMap.put("Quick Draw", new URL("http://haitai.jp/quick-draw"));
        haitaiUrlMap.put("Obsessed", new URL("http://haitai.jp/obsessed"));
        haitaiUrlMap.put("Nonstop", new URL("http://haitai.jp/non-stop"));
        haitaiUrlMap.put("Jack of All Trades", new URL("http://haitai.jp/jack-of-all-trades"));
        haitaiUrlMap.put("Perseverance", new URL("http://haitai.jp/perseverance-hush-hush-achievement"));
        haitaiUrlMap.put("Feel the burn", new URL("http://haitai.jp/feel-the-burn-hush-hush-achievement"));
        haitaiUrlMap.put("Time Dilation", new URL("http://haitai.jp/time-dilation-hush-hush-achievement"));
        haitaiUrlMap.put("Just One Second", new URL("http://haitai.jp/just-one-second-hush-hush-achievement"));
        haitaiUrlMap.put("Afterimage", new URL("http://haitai.jp/afterimage-hush-hush-achievement"));
        haitaiUrlMap.put("To The Core", new URL("http://haitai.jp/to-the-core-hush-hush-achievement"));
        haitaiUrlMap.put("Prepared", new URL("http://haitai.jp/prepared-hush-hush-achievement"));
        haitaiUrlMap.put("Eclipse", new URL("http://haitai.jp/eclipse-hush-hush-achievement"));
        haitaiUrlMap.put("Reckless Abandon", new URL("http://haitai.jp/reckless-abandon-hush-hush-achievement"));
        haitaiUrlMap.put("Tunnel Vision", new URL("http://haitai.jp/tunnel-vision-hush-hush-achievement"));
        haitaiUrlMap.put("Behold No Deception", new URL("http://haitai.jp/behold-no-deception-hush-hush-achievement"));
        haitaiUrlMap.put("Up For The Challenge", new URL("http://haitai.jp/up-for-the-challenge-hush-hush-achievement"));
        haitaiUrlMap.put("Lights Out", new URL("http://haitai.jp/lights-out-hush-hush-achievement"));
        haitaiUrlMap.put("Camera Shy", new URL("http://haitai.jp/camera-shy-hush-hush-achievement"));
        haitaiUrlMap.put("The Sum Of All Fears", new URL("http://haitai.jp/the-sum-of-all-fears-hush-hush-achievement"));
        haitaiUrlMap.put("Dekasight", new URL("http://haitai.jp/dekasight-hush-hush-achievement"));
        haitaiUrlMap.put("Unstoppable", new URL("http://haitai.jp/unstoppable-hush-hush-achievement"));
        haitaiUrlMap.put("Is This Real Life?", new URL("http://haitai.jp/is-this-real-life-hush-hush-achievement"));
        haitaiUrlMap.put("Hour Before The Dawn", new URL("http://haitai.jp/hour-before-the-dawn-hush-hush-achievement"));
        haitaiUrlMap.put("Slow And Steady", new URL("http://haitai.jp/slow-and-steady-hush-hush-achievement"));
        haitaiUrlMap.put("No Time To Spare", new URL("http://haitai.jp/no-time-to-spare-hush-hush-achievement"));
        haitaiUrlMap.put("Sognare", new URL("http://haitai.jp/sognare-hush-hush-achievement"));
        haitaiUrlMap.put("Realtor Extraordinaire", new URL("http://haitai.jp/realtor-extraordinaire-hush-hush-achievement"));
        haitaiUrlMap.put("Realität", new URL("http://haitai.jp/realitat-hush-hush-achievement"));
        haitaiUrlMap.put("Our Mechanical Benefactors", new URL("http://haitai.jp/our-mechanical-benefactors-hush-hush-achievement"));
        haitaiUrlMap.put("Meticulous", new URL("http://haitai.jp/meticulous-hush-hush-achievement"));
        haitaiUrlMap.put("Infinitesimal", new URL("http://haitai.jp/infinitesimal-hush-hush-achievement"));
        haitaiUrlMap.put("Equilibrium", new URL("http://haitai.jp/equilibrium-hush-hush-achievement"));
        haitaiUrlMap.put("Impeccable", new URL("http://haitai.jp/impeccable-hush-hush-achievement"));
        haitaiUrlMap.put("50/50", new URL("http://haitai.jp/50-50"));
        haitaiUrlMap.put("Elite", new URL("http://haitai.jp/elite-hush-hush-achievement"));
        haitaiUrlMap.put("You Can't Hide", new URL("http://haitai.jp/you-cant-hide"));
        haitaiUrlMap.put("Overconfident", new URL("http://haitai.jp/overconfident"));
        haitaiUrlMap.put("Feelin' It", new URL("http://haitai.jp/feelin-it"));
        haitaiUrlMap.put("Spooked", new URL("http://haitai.jp/spooked"));
        haitaiUrlMap.put("A meganekko approaches", new URL("http://haitai.jp/a-meganekko-approaches"));
        haitaiUrlMap.put("Thrill of the Chase", new URL("http://haitai.jp/thrill-of-the-chase"));
        haitaiUrlMap.put("The Girl in the Forest", new URL("http://haitai.jp/the-girl-in-the-forest"));
        haitaiUrlMap.put("True Torment", new URL("http://haitai.jp/true-torment"));
        haitaiUrlMap.put("The Firmament Moves", new URL("http://haitai.jp/the-firmament-moves"));
        haitaiUrlMap.put("Too Fast, Too Furious", new URL("http://haitai.jp/too-fast-too-furious"));
        haitaiUrlMap.put("500 Combo", new URL("http://haitai.jp/osu-combo-500"));
        haitaiUrlMap.put("750 Combo", new URL("http://haitai.jp/osu-combo-750"));
        haitaiUrlMap.put("1000 Combo", new URL("http://haitai.jp/osu-combo-1000"));
        haitaiUrlMap.put("2000 Combo", new URL("http://haitai.jp/osu-combo-2000"));
        haitaiUrlMap.put("Rising Star", new URL("http://haitai.jp/rising-star"));
        haitaiUrlMap.put("Constellation Prize", new URL("http://haitai.jp/constellation-prize"));
        haitaiUrlMap.put("Building Confidence", new URL("http://haitai.jp/building-confidence"));
        haitaiUrlMap.put("Insanity Approaches", new URL("http://haitai.jp/insanity-approaches"));
        haitaiUrlMap.put("These Clarion Skies", new URL("http://haitai.jp/these-clarion-skies"));
        haitaiUrlMap.put("Above and Beyond", new URL("http://haitai.jp/above-and-beyond"));
        haitaiUrlMap.put("Supremacy", new URL("http://haitai.jp/supremacy"));
        haitaiUrlMap.put("Absolution", new URL("http://haitai.jp/absolution"));
        haitaiUrlMap.put("Totality", new URL("http://haitai.jp/totality"));
        haitaiUrlMap.put("Business As Usual", new URL("http://haitai.jp/business-as-usual"));
        haitaiUrlMap.put("Building Steam", new URL("http://haitai.jp/building-steam"));
        haitaiUrlMap.put("Moving Forward", new URL("http://haitai.jp/moving-forward"));
        haitaiUrlMap.put("Paradigm Shift", new URL("http://haitai.jp/paradigm-shift"));
        haitaiUrlMap.put("Anguish Quelled", new URL("http://haitai.jp/anguish-quelled"));
        haitaiUrlMap.put("Never Give Up", new URL("http://haitai.jp/never-give-up"));
        haitaiUrlMap.put("Aberration", new URL("http://haitai.jp/aberration"));
        haitaiUrlMap.put("Finality", new URL("http://haitai.jp/finality"));
        haitaiUrlMap.put("Perfectionist", new URL("http://haitai.jp/perfectionist"));
        haitaiUrlMap.put("Rock Around The Clock", new URL("http://haitai.jp/rock-around-the-clock"));
        haitaiUrlMap.put("Time And A Half", new URL("http://haitai.jp/time-and-a-half"));
        haitaiUrlMap.put("Sweet Rave Party", new URL("http://haitai.jp/sweet-rave-party"));
        haitaiUrlMap.put("Blindsight", new URL("http://haitai.jp/blindsight"));
        haitaiUrlMap.put("Are You Afraid Of The Dark?", new URL("http://haitai.jp/are-you-afraid-of-the-dark"));
        haitaiUrlMap.put("Dial It Right Back", new URL("http://haitai.jp/dial-it-right-back"));
        haitaiUrlMap.put("Risk Averse", new URL("http://haitai.jp/risk-averse"));
        haitaiUrlMap.put("Slowboat", new URL("http://haitai.jp/slowboat"));
        haitaiUrlMap.put("Burned Out", new URL("http://haitai.jp/burned-out"));
        haitaiUrlMap.put("January/February 2017 Spotlight", new URL("http://haitai.jp/January-February-2017-Spotlight"));
        haitaiUrlMap.put("March 2017 Spotlight", new URL("http://haitai.jp/March-2017-Spotlight"));
        haitaiUrlMap.put("April 2017 Spotlight", new URL("http://haitai.jp/April-2017-Spotlight"));
        haitaiUrlMap.put("May 2017 Spotlight", new URL("http://haitai.jp/May-2017-Spotlight"));
        haitaiUrlMap.put("June 2017 Spotlight", new URL("http://haitai.jp/June-2017-Spotlight"));
        haitaiUrlMap.put("July 2017 Spotlight", new URL("http://haitai.jp/July-2017-Spotlight"));
        haitaiUrlMap.put("August 2017 Spotlight", new URL("http://haitai.jp/August-2017-Spotlight"));
        haitaiUrlMap.put("My First Don", new URL("http://haitai.jp/rising-star"));
        haitaiUrlMap.put("A Slice Of Life", new URL("http://haitai.jp/rising-star/"));
        haitaiUrlMap.put("First Steps", new URL("http://haitai.jp/rising-star/"));
        haitaiUrlMap.put("Katsu Katsu Katsu", new URL("http://haitai.jp/constellation-prize/"));
        haitaiUrlMap.put("Dashing Ever Forward", new URL("http://haitai.jp/constellation-prize/"));
        haitaiUrlMap.put("No Normal Player", new URL("http://haitai.jp/constellation-prize/"));
        haitaiUrlMap.put("Face Your Demons", new URL("http://haitai.jp/insanity-approaches/"));
        haitaiUrlMap.put("Hyperdash ON!", new URL("http://haitai.jp/insanity-approaches/"));
        haitaiUrlMap.put("Hyperspeed", new URL("http://haitai.jp/insanity-approaches/"));
        haitaiUrlMap.put("The Demon Within", new URL("http://haitai.jp/these-clarion-skies/"));
        haitaiUrlMap.put("It's Raining Fruit", new URL("http://haitai.jp/these-clarion-skies/"));
        haitaiUrlMap.put("Ever Onwards", new URL("http://haitai.jp/these-clarion-skies/"));
        haitaiUrlMap.put("Drumbreaker", new URL("http://haitai.jp/above-and-beyond/"));
        haitaiUrlMap.put("Fruit Ninja", new URL("http://haitai.jp/above-and-beyond/"));
        haitaiUrlMap.put("Another Surpassed", new URL("http://haitai.jp/above-and-beyond/"));
        haitaiUrlMap.put("The Godfather", new URL("http://haitai.jp/supremacy/"));
        haitaiUrlMap.put("Dreamcatcher", new URL("http://haitai.jp/supremacy/"));
        haitaiUrlMap.put("Extra Credit", new URL("http://haitai.jp/supremacy/"));
        haitaiUrlMap.put("Rhythm Incarnate", new URL("http://haitai.jp/absolution/"));
        haitaiUrlMap.put("Lord of the Catch", new URL("http://haitai.jp/absolution/"));
        haitaiUrlMap.put("Maniac", new URL("http://haitai.jp/absolution/"));
        haitaiUrlMap.put("Keeping Time", new URL("http://haitai.jp/totality/"));
        haitaiUrlMap.put("Sweet And Sour", new URL("http://haitai.jp/totality/"));
        haitaiUrlMap.put("Keystruck", new URL("http://haitai.jp/totality/"));
        haitaiUrlMap.put("To Your Own Beat", new URL("http://haitai.jp/business-as-usual/"));
        haitaiUrlMap.put("Reaching The Core", new URL("http://haitai.jp/business-as-usual/"));
        haitaiUrlMap.put("Keying In", new URL("http://haitai.jp/business-as-usual/"));
        haitaiUrlMap.put("Big Drums", new URL("http://haitai.jp/building-steam/"));
        haitaiUrlMap.put("Clean Platter", new URL("http://haitai.jp/building-steam/"));
        haitaiUrlMap.put("Hyperflow", new URL("http://haitai.jp/building-steam/"));
        haitaiUrlMap.put("Adversity Overcome", new URL("http://haitai.jp/moving-forward/"));
        haitaiUrlMap.put("Between The Rain", new URL("http://haitai.jp/moving-forward/"));
        haitaiUrlMap.put("Breakthrough", new URL("http://haitai.jp/moving-forward/"));
        haitaiUrlMap.put("Demonslayer", new URL("http://haitai.jp/paradigm-shift/"));
        haitaiUrlMap.put("Addicted", new URL("http://haitai.jp/paradigm-shift/"));
        haitaiUrlMap.put("Everything Extra", new URL("http://haitai.jp/paradigm-shift/"));
        haitaiUrlMap.put("Rhythm's Call", new URL("http://haitai.jp/anguish-quelled/"));
        haitaiUrlMap.put("Quickening", new URL("http://haitai.jp/anguish-quelled/"));
        haitaiUrlMap.put("Level Breaker", new URL("http://haitai.jp/anguish-quelled/"));
        haitaiUrlMap.put("Time Everlasting", new URL("http://haitai.jp/never-give-up/"));
        haitaiUrlMap.put("Supersonic", new URL("http://haitai.jp/never-give-up/"));
        haitaiUrlMap.put("Step Up", new URL("http://haitai.jp/never-give-up/"));
        haitaiUrlMap.put("The Drummer's Throne", new URL("http://haitai.jp/aberration/"));
        haitaiUrlMap.put("Dashing Scarlet", new URL("http://haitai.jp/aberration/"));
        haitaiUrlMap.put("Not Even Trying", new URL("http://haitai.jp/building-confidence"));
        haitaiUrlMap.put("Zesty Disposition", new URL("http://haitai.jp/building-confidence"));
        haitaiUrlMap.put("Impulse Drive", new URL("http://haitai.jp/building-confidence"));
        haitaiUrlMap.put("Behind The Veil", new URL("http://haitai.jp/aberration/"));
        haitaiUrlMap.put("MOtOLOiD", new URL("http://haitai.jp/MOtOLOiD"));
        haitaiUrlMap.put("September 2017 Spotlight", new URL("http://haitai.jp/september-2017-spotlight"));
        haitaiUrlMap.put("October 2017 Spotlight", new URL("http://haitai.jp/october-2017-spotlight"));
        haitaiUrlMap.put("November 2017 Spotlight", new URL("http://haitai.jp/november-2017-spotlight"));

    }
}
