package cc.moecraft.irc.osubot.language;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.CommandManager;
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
        text = replaceVariables(text);

        /*if (Main.getConfig().getBoolean("BotProperties.DisableChannelReply"))
            event.respondPrivateMessage(text);
        else
            event.respond(text);*/

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

        respond(event, languageFileManager.get(lang, placeholder));
    }

    /**
     * 带参数的回复
     * @param event 事件
     * @param lang 语言
     * @param placeholder 语言节点
     * @param args 参数
     */
    public void respondWithFormat(GenericMessageEvent event, String lang, String placeholder, Object ... args)
    {
        respond(event, String.format(languageFileManager.get(lang, placeholder), args));
    }

    public LanguageFileManager getLanguageFileManager()
    {
        return languageFileManager;
    }

    public void setLanguageFileManager(LanguageFileManager languageFileManager)
    {
        this.languageFileManager = languageFileManager;
    }

    /**
     * 替换所有变量
     * @param original 源字符串
     * @return 替换后的字符串
     */
    public String replaceVariables(String original)
    {
        return original
                .replace("%prefix%", CommandManager.getPrefix());
    }
}
