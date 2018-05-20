package cc.moecraft.scripts;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.achievement.AchievementManager;
import cc.moecraft.logger.DebugLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static cc.moecraft.scripts.AchievementClassGenerator.getNoSpaceName;
import static cc.moecraft.scripts.LanguageFileWriteMethodGenerator.createFileOrOverride;
import static cc.moecraft.scripts.LanguageFileWriteMethodGenerator.getAllJavaFilesInString;

/**
 * 此类由 Hykilpikonna 在 2018/05/20 创建!
 * Created by Hykilpikonna on 2018/05/20!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementsLanguageFileConverter
{
    private static String srcPath = "./src/main/java/";
    private static DebugLogger logger = new DebugLogger("AchievementsLanguageFileConverter", true);

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException {
        AchievementManager achievementManager = new AchievementManager();
        StringBuilder languageFileWriteMethodBuilder = new StringBuilder();

        HashMap<File, String> tempFiles = getAllJavaFilesInString(srcPath);
        HashMap<File, String> allFilesInString = new HashMap<>();

        HashMap<String, Achievement> achievements = achievementManager.getAchievements();

        tempFiles.forEach((file, string) ->
        {
            if (file.getName().contains("Achievement")) allFilesInString.put(file, string);
        });

        int count = 0;

        for (Map.Entry<String, Achievement> achievement : achievements.entrySet())
        {
            count ++;

            Map.Entry<File, String> file = getFileFromAchievement(allFilesInString, achievement.getValue());

            if (file == null)
            {
                logger.error("成就: " + achievement.getKey() + "未找到对应文件");
                continue;
            }

            String languageNode = "achievement." + getNoSpaceName(achievement.getValue().getName())
                    .replaceAll("[^A-Za-z0-9 ]", "")
                    .replace(",", "")
                    .toLowerCase();
            String value = achievement.getValue().getTutorial();

            languageFileWriteMethodBuilder.append(String.format("addDefault(\"%s\", \"%s\");", languageNode, value)).append("\n");
            String newFileContent = file.getValue().replace(value, languageNode);

            File targetFile = file.getKey();

            createFileOrOverride(targetFile);

            FileWriter fileWriter = new FileWriter(targetFile);
            fileWriter.write(newFileContent);
            fileWriter.close();

            logger.log("完成度: " + count + "/" + achievements.size() + " (" + Math.round((double) count / (double) achievements.size() * 10000d) / 100d + "%)");
            //logger.log("----------------------------------------------------------------------------------------------------------------------");
        }

        logger.log("Language File Write Method: " + languageFileWriteMethodBuilder.toString());
    }

    private static Map.Entry<File, String> getFileFromAchievement(HashMap<File, String> allFilesInString, Achievement achievement)
    {
        for (Map.Entry<File, String> entry : allFilesInString.entrySet())
        {
            String file = entry.getValue();
            if (file.contains(achievement.getName()) && file.contains(achievement.getSlug()) && file.contains(achievement.getDescription()))
                return entry;
        }
        return null;
    }
}
