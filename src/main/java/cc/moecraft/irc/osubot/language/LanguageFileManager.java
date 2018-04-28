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
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class LanguageFileManager
{
    private Map<String, LanguageFile> languageFileMap;

    public static final String DEFAULT_LANG = "en";

    private enum OfficiallySupportedLanguages
    {
        zh, en
    }

    public LanguageFileManager()
    {
        languageFileMap = new HashMap<>();
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
        try
        {
            OfficiallySupportedLanguages.valueOf(lang);
            return true;
        }
        catch (Exception ignored)
        {
            return new File("./" + Main.PATH + "/Language@" + lang + ".yml").exists();
        }
    }

    public class LanguageFile extends Config
    {
        String lang;

        LanguageFile(String lang)
        {
            super(Main.VERSION, Main.PATH, "Language", "yml", false, true, true);
            this.lang = lang;
            writeDefaultConfig();
            save();
        }

        public String get(String placeholder)
        {
            return getString(placeholder);
        }

        @Override
        public void readConfig() {

        }

        @Override
        public void writeConfig() {

        }

        @Override
        public void writeDefaultConfig()
        {
            if (lang == null) return;

            if (lang.equals(OfficiallySupportedLanguages.en.name()))
            {
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
            else if (lang.equals(OfficiallySupportedLanguages.zh.name()))
            {
                addDefault("Commands.Help.HELP_TEXT", new String[]{
                        "<---------= 帮助菜单 =--------->",
                        "<---= 指令:",
                        "  [>= !help    打开这个菜单",
                        "  [>= !cmd     显示所有可用指令",
                        "  [>= !ping    啪!",
                });
                addDefault("Commands.Errors.NOT_A_COMMAND", "NOT A COMMAND: 不是指令 ( 输入%shelp显示帮助 )");
                addDefault("Commands.Errors.UNKNOWN_COMMAND", "UNKNOWN COMMAND: 未知指令 ( 输入%shelp显示帮助 )");
                addDefault("Commands.CommandList.COMMANDS_TEXT", "所有指令: %s");
            }
            else
            {
                Main.getLogger().debug("ERROR - 不会有这种可能性");
            }
        }
    }
}
