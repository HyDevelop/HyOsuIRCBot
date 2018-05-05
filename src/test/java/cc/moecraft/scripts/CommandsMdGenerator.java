package cc.moecraft.scripts;

import cc.moecraft.irc.osubot.utils.FileUtils;
import cc.moecraft.logger.DebugLogger;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cc.moecraft.irc.osubot.utils.FileUtils.getAllJavaFiles;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class CommandsMdGenerator
{
    private static DebugLogger logger = new DebugLogger("CommandsMdGenerator", true);

    // private static Pattern regex = Pattern.compile("/\\*\\*([^*]|\\*(?!/))*?用法.*?\\*/", Pattern.DOTALL);
    private static Pattern regex2 = Pattern.compile(" {5}\\* 用法:([^*]|\\*(?!/))*?\\* @param");

    public static void main(String[] args) throws IOException
    {
        ArrayList<String> javaDocComments = getJavaDocComments(new File("D:\\Temp"));
    }

    /**
     * 获取所有用法java文档
     * @param path 路径
     * @return 所有JavaDocs字符串
     */
    public static ArrayList<String> getJavaDocComments(File path) throws IOException
    {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<File> allFiles = getAllJavaFiles(FileUtils.getAllFiles(path));

        for (File file : allFiles)
        {
            String content = FileUtils.readFileAsString(file);

            Matcher matcher = regex2.matcher(content);
            while (matcher.find())
            {
                result.add(matcher.group());
            }
        }

        return result;
    }
}
