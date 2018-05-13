package java.cc.moecraft;

import cc.moecraft.irc.osubot.utils.DownloadUtils;
import com.google.gson.JsonElement;

import java.net.MalformedURLException;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class JSONClassGenerator
{
    public static void main(String[] args) throws MalformedURLException {
        StringBuilder builder = new StringBuilder();

        builder.append("" +
                "import lombok.*;\n" +
                "\n" +
                "/**\n" +
                " * 此类由 Hykilpikonna 的 JSONClassGenerator 生成!\n" +
                " * Github: https://github.com/hykilpikonna\n" +
                " * QQ: admin@moecraft.cc -OR- 871674895\n" +
                " */\n" +
                "@EqualsAndHashCode(callSuper = true)\n" +
                "@Data @Builder @NoArgsConstructor @AllArgsConstructor\n" +
                "public class UserData extends DataBase\n" +
                "{");

        JsonElement element = DownloadUtils.getJsonElementFromURL("https://ameobea.me/osutrack/api/get_changes.php?user=hykilpikonna&mode=0", 5000);

        assert element != null;
        element.getAsJsonObject().keySet().forEach(key -> builder.append(String.format("\n    private %s %s;", getFriendlyName(element.getAsJsonObject().get(key).getClass().getName()), key)));

        builder.append("\n" +
                "}");

        System.out.println(builder.toString());
    }

    public static String getFriendlyName(String originalClassName)
    {
        if (originalClassName.contains("Int")) return "long";
        if (originalClassName.contains("Boo")) return "boolean";
        if (originalClassName.contains("Str")) return "String";
        return originalClassName;
    }
}
