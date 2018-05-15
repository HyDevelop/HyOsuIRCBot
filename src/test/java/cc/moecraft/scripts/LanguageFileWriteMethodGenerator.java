package cc.moecraft.scripts;

import cc.moecraft.logger.DebugLogger;
import cc.moecraft.scripts.templib.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类由 Hykilpikonna 在 2018/05/15 创建!
 * Created by Hykilpikonna on 2018/05/15!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class LanguageFileWriteMethodGenerator
{
    private static DebugLogger logger = new DebugLogger("LanguageFileWriteMethodGenerator", true);

    private static final File rootDir = new File("./src/main");
    private static final File languageFileWriteMethodFileGeneratePath = new File("./languageFileConfig.txt");
    private static final File javaFileGenerateDir = new File("./JavaFiles/");
    private static final Pattern regexToFindLanguageText = Pattern.compile("(?<=Main\\.getMessenger\\(\\)\\.respond\\(event, \")(.*)(?=\"\\);)");

    public static void main(String[] args) throws IOException
    {
        Map<String, String> languageNodes = generateLanguageNodesAsMap(getAllJavaFilesInString(String.valueOf(rootDir)));

        logger.debug("Language Nodes: " + languageNodes);
    }

    public static HashMap<String, String> generateLanguageNodesAsMap(HashMap<File, String> fileContentMap) throws IOException
    {
        HashMap<String, String> result = new HashMap<>();

        for (Map.Entry<File, String> fileContentEntry : fileContentMap.entrySet())
        {
            // 每个文件

            String fileName = FilenameUtils.removeExtension(fileContentEntry.getKey().getName());
            String[] fileContent = fileContentEntry.getValue().split("\n");
            StringBuilder modifiedFileContentBuilder = new StringBuilder();
            boolean modified = false;

            for (int i = 0; i < fileContent.length; i++)
            {
                // 每行

                Matcher matcher = regexToFindLanguageText.matcher(fileContent[i]);

                if (matcher.find())
                {
                    String text = matcher.group();
                    String languageNode = fileName + "_" + i;

                    result.put(text, languageNode);
                    modifiedFileContentBuilder.append(fileContent[i]
                            .replace("Main.getMessenger().respond(event, \"", "return MultiLanguageText.languageNode(\"")
                            .replaceAll("(?<=\")(.*)(?=\")", languageNode)).append("\n");

                    modified = true;
                }
                else
                {
                    modifiedFileContentBuilder.append(fileContent[i]).append("\n");
                }
            }

            if (modified) // 保存修改
            {
                File targetFile = new File(javaFileGenerateDir, fileContentEntry.getKey().getPath());

                createFileOrOverride(targetFile);

                FileWriter fileWriter = new FileWriter(targetFile);
                fileWriter.write(modifiedFileContentBuilder.toString());
                fileWriter.close();
            }
        }

        return result;
    }

    /**
     * 读取所有文件, 返回文件名对应文件内容的Map
     * @param dir 根目录
     * @return MAp
     */
    public static HashMap<File, String> getAllJavaFilesInString(String dir)
    {
        ArrayList<File> files = FileUtils.getAllJavaFiles(FileUtils.getAllFiles(new File(dir)));

        HashMap<File, String> result = new HashMap<>();

        files.forEach(file ->
        {
            try
            {
                result.put(file, FileUtils.readFileAsString(file));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });

        return result;
    }

    public static void createFileOrOverride(File file) throws IOException
    {
        if (!cc.moecraft.yaml.utils.FileUtils.createFile(file))
        {
            logger.log("文件已存在, 正在覆盖");

            file.delete();
            createFileOrOverride(file);
        }
    }
}
