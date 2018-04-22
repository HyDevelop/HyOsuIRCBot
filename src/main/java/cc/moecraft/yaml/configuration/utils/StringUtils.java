package cc.moecraft.yaml.configuration.utils;

public class StringUtils
{
    public static String pad(int fieldWidth, char padChar, String s)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }
}
