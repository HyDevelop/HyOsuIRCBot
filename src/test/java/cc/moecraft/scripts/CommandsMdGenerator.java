package cc.moecraft.scripts;

import cc.moecraft.irc.osubot.utils.FileUtils;
import cc.moecraft.logger.DebugLogger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.github.pcre.Pcre;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    private static Pattern regex = Pattern.compile("/\\*\\*([^*]|\\*(?!/))*?用法.*?\\*/", Pattern.DOTALL);
    private static Pattern regexFindPermission = Pattern.compile("(?<=public String permissionRequired\\(\\)\\n    \\{\\n        return \\\")(.*)(?=\\\"\\;\n    \\})", Pattern.DOTALL);
    /*private static Pattern regexFindPermission = Pattern.compile("" +
            "(?<=@Override\\n" +
            " {4}public String permissionRequired\\(\\)\\n" +
            " {4}\\{\\n" +
            " {8}return \")(.*)(?=\";.*})", Pattern.DOTALL);*/
    private static Pattern regexFindJavaDocComments = Pattern.compile(" {5}\\* 用法:([^*]|\\*(?!/))*?\\* @param");
    private static Pattern regexFindExtend = Pattern.compile("public class .* extends Command");

    public static void main(String[] args) throws IOException
    {
        ArrayList<DataSet> javaDocComments = getJavaDocComments(new File("D:\\Temp"));
        logger.log("Generated: " + generate(javaDocComments));
    }

    public static String generate(ArrayList<DataSet> javaDocComments)
    {
        StringBuilder result = new StringBuilder();

        // 分类
        ArrayList<DataSet> userOsu = new ArrayList<>();
        ArrayList<DataSet> userFun = new ArrayList<>();
        ArrayList<DataSet> admins = new ArrayList<>();

        for (DataSet dataSet : javaDocComments)
        {
            if (dataSet.getPermission().contains("irc.user.regular.osu"))
            {
                userOsu.add(dataSet);
            }
            if (dataSet.getPermission().contains("irc.user.regular.fun"))
            {
                userFun.add(dataSet);
            }
            if (dataSet.getPermission().contains("irc.admin"))
            {
                admins.add(dataSet);
            }
        }

        // 生成
        result.append("\n## 指令帮助:\n\n#### Osu指令:\n\n");

        appendData(result, userOsu);

        result.append("\n\n#### 彩蛋指令:\n\n");

        appendData(result, userFun);

        result.append("\n\n#### 管理指令:\n\n");

        appendData(result, admins);

        return result.toString();
    }

    public static void appendData(StringBuilder builder, ArrayList<DataSet> dataSets)
    {
        for (DataSet dataSet : dataSets)
        {
            StringBuilder javaDocs = new StringBuilder();

            for (String javadoc : dataSet.getJavaDocs())
            {
                String[] split = javadoc.split("\n");

                for (String line : split)
                {
                    logger.debug("处理: " + line);
                    if (line.contains("用法")) continue;
                    if (line.contains("@param")) continue;
                    if (line.equals(split[split.length - 2])) continue;

                    line = line.replaceAll(".*\\*", "       ");
                    javaDocs.append(line).append("\n");
                    logger.debug("添加");
                }
            }

            builder.append(javaDocs);
        }
    }

    /**
     * 获取所有用法java文档
     * @param path 路径
     * @return 所有JavaDocs字符串
     */
    public static ArrayList<DataSet> getJavaDocComments(File path) throws IOException
    {
        ArrayList<DataSet> result = new ArrayList<>();
        ArrayList<File> allFiles = getAllJavaFiles(FileUtils.getAllFiles(path));

        for (File file : allFiles)
        {
            logger.debug("正在处理文件: " + file.toString());

            String content = FileUtils.readFileAsString(file);

            if (regexFindExtend.matcher(content).find())
            {
                DataSet dataSet = new DataSet(new ArrayList<>(), "");

                String permissionAndTheRest = Pcre.preg_match_all(regexFindPermission, content)[0];
                dataSet.setPermission(permissionAndTheRest.contains("\";") ? Pcre.preg_match_all("(.*)(?=\\\"\\;\\n    \\})", permissionAndTheRest)[0] : permissionAndTheRest);
                logger.debug("Perm: " + dataSet.getPermission());

                Matcher matcherJDoc = regexFindJavaDocComments.matcher(content);
                while (matcherJDoc.find())
                {
                    dataSet.getJavaDocs().add(matcherJDoc.group());
                }

                result.add(dataSet);
            }
        }

        return result;
    }

    @Data @AllArgsConstructor
    public static class DataSet
    {
        ArrayList<String> javaDocs;
        String permission;
    }
}
