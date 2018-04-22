package cc.moecraft.irc.osubot.language;

import org.pircbotx.hooks.types.GenericMessageEvent;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class Messenger
{
    private LanguageFileManager languageFileManager;

    public Messenger()
    {
        languageFileManager = new LanguageFileManager();
    }

    public void respond(GenericMessageEvent event, String text)
    {
        event.respond(text);
    }

    /**
     * 用语言节点回复
     * @param event 事件
     * @param lang 语言
     * @param placeholder 语言节点
     */
    public void respond(GenericMessageEvent event, String lang, String placeholder)
    {
        if (lang == null || lang.equals("")) lang = LanguageFileManager.DEFAULT_LANG;

        event.respond(languageFileManager.get(lang, placeholder));
    }

    /**
     * 带参数的回复
     * @param event 事件
     * @param lang 语言
     * @param placeholder 语言节点
     * @param args 参数
     */
    public void respondWithParam(GenericMessageEvent event, String lang, String placeholder, Object[] args)
    {
        event.respond(String.format(languageFileManager.get(lang, placeholder), args));
    }

    public LanguageFileManager getLanguageFileManager()
    {
        return languageFileManager;
    }

    public void setLanguageFileManager(LanguageFileManager languageFileManager)
    {
        this.languageFileManager = languageFileManager;
    }
}
