package cc.moecraft.irc.osubot.language;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.yaml.Config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class LanguageFileManager
{
    private Map<String, LanguageFile> languageFileMap; // 语言名字对应文件
    public static final String DEFAULT_LANG = "cn";

    public LanguageFileManager()
    {
        languageFileMap = new HashMap<>();
        getFile(DEFAULT_LANG); // 创建默认语言文件
    }

    public String get(String lang, String placeholder)
    {
        return getFile(lang).get(placeholder);
    }

    public LanguageFile getFile(String lang)
    {
        lang = getLang(lang);

        if (languageFileMap.containsKey(lang))
        {
            return languageFileMap.get(lang);
        }
        else
        {
            languageFileMap.put(lang, new LanguageFile(lang));
            return getFile(lang);
        }
    }

    public String getLang(String original)
    {
        return !hasLanguageFile(original) ? DEFAULT_LANG : original;
    }

    public boolean hasLanguageFile(String lang)
    {
        return new LanguageFile(lang).exists();
    }

    public class LanguageFile extends Config
    {
        String lang;

        LanguageFile(String lang)
        {
            super(Main.PATH + File.separator + "language", "Language@" + lang, "yml", false, false);
            this.lang = lang;
            if (exists() || DEFAULT_LANG.equals(lang)) initialize();
        }

        /**
         * 判断语言文件是否存在
         * @return 是否存在
         */
        public boolean exists()
        {
            return getConfigFile().exists();
        }

        /**
         * 获取语言消息
         * @param languageNode 语言节点
         * @return 语言消息
         */
        public String get(String languageNode)
        {
            return getString(languageNode);
        }

        @Override
        public void readConfig() {

        }

        @Override
        public void writeDefaultConfig()
        {
            if (lang == null) return;

            addDefault("Commands.Help.HELP_TEXT", new String[]{
                    "<---------= HELP MENU =--------->",
                    "<---= Commands:",
                    "  [>= !help    Display this menu",
                    "  [>= !cmd     List all available commands",
                    "  [>= !ping    Pong!",
            });
            addDefault("Commands.Errors.NOT_A_COMMAND", "Not a command. (Type %shelp to show the help menu)");
            addDefault("Commands.Errors.UNKNOWN_COMMAND", "Unknown command.  (Type %shelp to show the help menu)");
            addDefault("Commands.CommandList.COMMANDS_TEXT", "Command List: %s");
        }

        // 所有获取/写入的语言节点全用小写

        @Override
        public void addDefault(String path, Object value)
        {
            super.addDefault(path.toLowerCase(), value);
        }

        @Override
        public String getString(String path)
        {
            return super.getString(path.toLowerCase());
        }
    }
}
