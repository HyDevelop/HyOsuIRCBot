package cc.moecraft.irc.osubot.utils;

import java.util.Scanner;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class StringUtils
{
    /**
     * 添加HTTP请求参数
     * @param builder 字符串构造器
     * @param key 键
     * @param value 值
     */
    public static StringBuilder addParameter(StringBuilder builder, String key, String value)
    {
        if (!builder.toString().endsWith("&") || !builder.toString().endsWith("?"))
        {
            builder.append("&");
        }
        return builder.append(key).append("=").append(value);
    }

    /**
     * 判断当前版本和最新版本的关系
     *
     * 如果当前版本小于最新版本, 返回1,
     * 如果当前版本大于最新版本, 返回-1,
     * 如果当前版本等于最新版本, 返回0
     *
     * 注意: 所有非数字字符都会被忽略
     *
     * 例子: 0.1.6.9 和 0.1.6.9 返回0
     * 例子: 0.1.6.9 和 0.1.7.0 返回1
     * 例子: 0.1.69  和 0.17.0  返回1
     * 例子: 0.16.9  和 0.1.70  返回-1
     *
     * @param currentVersion 当前版本
     * @param latestVersion 最新版本
     * @return 对比值
     */
    public static int versionComparison(String currentVersion, String latestVersion)
    {
        if (currentVersion == null || latestVersion == null) return 0;
        String[] currentVersionAfterSplit = removeInNumeric(currentVersion).split("\\.");
        String[] latestVersionAfterSplit = removeInNumeric(latestVersion).split("\\.");

        int currentLength = currentVersionAfterSplit.length;
        int latestLength = latestVersionAfterSplit.length;

        for (int i = 0; i < Math.max(currentLength, latestLength); i++)
        {
            int currentVersionAtI = i < currentLength ? Integer.parseInt(currentVersionAfterSplit[i]) : 0;
            int latestVersionAtI = i < latestLength ? Integer.parseInt(latestVersionAfterSplit[i]) : 0;

            if (currentVersionAtI < latestVersionAtI) return 1;
            if (currentVersionAtI > latestVersionAtI) return -1;
        }
        return 0;
    }

    /**
     * 去掉字符串里除了数字/小数点外的字符
     *
     * @param string 源
     * @return 处理后
     */
    public static String removeInNumeric(String string)
    {
        if (string == null || string.equals("")) return "";

        StringBuilder output = new StringBuilder();
        for (Character aChar : string.toCharArray())
        {
            if (Character.isDigit(aChar)) output.append(aChar);
            if (aChar == '.') output.append('.');
        }
        return output.toString();
    }

    /**
     * 过滤html标签
     */
    public static String guoHtml(String s) {
        return s.replaceAll("<[.[^<]]*>", "").trim();
    }

    /**
     * 一行内所有首字母大写
     * @param line 一行
     * @return 首字母都大写了的一行
     */
    public static String capitalizeFirstLetterOfEachWord(String line)
    {
        StringBuilder result = new StringBuilder();
        Scanner lineScan = new Scanner(line);
        while(lineScan.hasNext())
        {
            String word = lineScan.next();
            result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return result.toString();
    }

    /**
     * 判断字符串是不是数字
     * @param string 字符串
     * @return 是不是数字
     */
    public static boolean isNumeric(String string)
    {
        return string != null && string.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * 替换最后一个
     * @param text 源字符串
     * @param regex 要替换从的
     * @param replacement 要替换成的
     * @return 替换后的字符串
     */
    public static String replaceLast(String text, String regex, String replacement)
    {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }
}
