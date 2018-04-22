package cc.moecraft.irc.osubot.language;

import cc.moecraft.api.Config;
import cc.moecraft.irc.osubot.Main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static cc.moecraft.irc.osubot.Main.VERSION;

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

            }
            else if (lang.equals(OfficiallySupportedLanguages.zh.name()))
            {

            }
            else
            {
                Main.getLogger().debug("ERROR - 不会有这种可能性");
            }
        }
    }
}
