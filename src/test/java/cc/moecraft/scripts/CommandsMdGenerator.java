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
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cc.moecraft.irc.osubot.utils.FileUtils.getAllJavaFiles;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
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
        ArrayList<DataSet> javaDocComments = getJavaDocComments(new File("./src/"));
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

        // 排序
        userOsu.sort(DataSet::compareTo);
        userFun.sort(DataSet::compareTo);
        admins.sort(DataSet::compareTo);

        // 生成
        result.append("\n## 指令帮助:\n\n#### Osu指令:\n\n");

        appendData(result, userOsu);

        result.append("\n#### 彩蛋指令:\n\n");

        appendData(result, userFun);

        result.append("\n#### 管理指令:\n\n");

        appendData(result, admins);

        return result.toString();
    }

    public static void appendData(StringBuilder builder, ArrayList<DataSet> dataSets)
    {
        boolean switched = false;

        for (DataSet dataSet : dataSets)
        {
            logger.debug("正在处理文件: " + dataSet.getFile().getPath() + "------------------");
            StringBuilder javaDocs = new StringBuilder();

            String javadoc = dataSet.getJavaDocs();

            String[] split = javadoc.split("\n");
            StringBuilder oneJavaDoc = new StringBuilder();

            for (String line : split)
            {
                logger.debug("处理: " + line);
                if (line.contains("用法")) continue;
                if (line.contains("@param")) continue;
                if (split.length > 1 && line.equals(split[split.length - 2])) continue;

                line = line.replaceAll(".*\\*", "       ");
                if (line.replace(" ", "").equals(".")) line = "       ";
                oneJavaDoc.append(line).append("\n");
                logger.debug("添加");
            }

            javaDocs.append(oneJavaDoc);
            if (oneJavaDoc.toString().split("\n").length > 3) javaDocs.append("\n\n");
            else if (!switched)
            {
                builder.append("         其他指令:\n");
                switched = true;
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

            if (file.getName().contains("Generator")) continue;

            String content = FileUtils.readFileAsString(file);

            if (!content.contains("用法")) continue;

            if (regexFindExtend.matcher(content).find())
            {
                DataSet dataSet = new DataSet("", "", file);

                String permissionAndTheRest = Pcre.preg_match_all(regexFindPermission, content)[0];
                dataSet.setPermission(permissionAndTheRest.contains("\";") ? Pcre.preg_match_all("(.*)(?=\\\"\\;\\n    \\})", permissionAndTheRest)[0] : permissionAndTheRest);
                logger.debug("Perm: " + dataSet.getPermission());

                Matcher matcherJDoc = regexFindJavaDocComments.matcher(content);
                if (matcherJDoc.find())
                {
                    dataSet.setJavaDocs(matcherJDoc.group());
                }

                result.add(dataSet);
            }
        }

        return result;
    }

    @Data @AllArgsConstructor
    public static class DataSet implements Comparable<DataSet>
    {
        String javaDocs;
        String permission;
        File file;

        @Override
        public int compareTo(DataSet o)
        {
            return o.javaDocs.split("\n").length - this.javaDocs.split("\n").length;
        }
    }
}
