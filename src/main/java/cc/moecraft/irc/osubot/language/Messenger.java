package cc.moecraft.irc.osubot.language;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.osu.data.web.WebsiteUserData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
@AllArgsConstructor @Data
public class Messenger
{
    @Getter
    private LanguageFileManager languageFileManager;
    private Map<String, String> globalVariables;

    /**
     * 获取一个多语言对象的消息文字
     * @param event 事件
     * @param multiLanguageText 多语言对象
     * @return 消息文字
     */
    public String getText(GenericMessageEvent event, MultiLanguageText multiLanguageText)
    {
        return resolveMLT(multiLanguageText, Main.getOsuHtmlUtils().getWebUserData(event.getUser().getNick()).getCountry().getCode());
    }

    /**
     * 用多语言对象回复一条IRC消息
     * @param event IRC事件
     * @param multiLanguageText 多语言对象
     * @param lang 语言
     */
    public void respondIRC(GenericMessageEvent event, MultiLanguageText multiLanguageText, String lang)
    {
        String text = resolveMLT(multiLanguageText, lang);

        if (text.equals("")) return;

        event.respond(text);
    }

    /**
     * 用多语言对象给用户回复一条IRC消息
     * @param event IRC事件
     * @param multiLanguageText 多语言对象
     * @param user 用户对象
     */
    public void respondIRCWithWebUserData(GenericMessageEvent event, MultiLanguageText multiLanguageText, WebsiteUserData user)
    {
        respondIRC(event, multiLanguageText, user.getCountry().getCode());
    }

    /**
     * 用多语言对象给用户回复一条IRC消息
     * @param event IRC事件
     * @param multiLanguageText 多语言对象
     * @param username 用户名
     */
    public void respondIRCWithUsername(GenericMessageEvent event, MultiLanguageText multiLanguageText, String username)
    {
        respondIRCWithWebUserData(event, multiLanguageText, Main.getOsuHtmlUtils().getWebUserData(username));
    }

    /**
     * 用多语言对象给事件用户回复一条IRC消息
     * @param event IRC事件
     * @param multiLanguageText 多语言对象
     */
    public void respondIRC(GenericMessageEvent event, MultiLanguageText multiLanguageText)
    {
        respondIRCWithWebUserData(event, multiLanguageText, Main.getOsuHtmlUtils().getWebUserData(event.getUser().getNick()));
    }

    /**
     * 解析多语言对象为字符串
     * @param multiLanguageText 多语言对象
     * @param lang 语言
     * @return 解析后的字符串
     */
    public String resolveMLT(MultiLanguageText multiLanguageText, String lang)
    {
        String text = multiLanguageText.getText(lang);

        if (text.equals("")) return "";

        text = replaceVariables(text, globalVariables);
        text = replaceVariables(text, multiLanguageText.getVariables());

        if (multiLanguageText.getPrefix() != null) text = multiLanguageText.getPrefix().getText(lang) + text;
        if (multiLanguageText.getSuffix() != null) text = text + multiLanguageText.getSuffix().getText(lang);

        return text;
    }

    /**
     * 替换变量
     * @param text 源字符串
     * @param variables 变量组
     * @return 替换后的字符串
     */
    public static String replaceVariables(String text, Map<String, String> variables)
    {
        for (Map.Entry<String, String> variableEntry : variables.entrySet())
        {
            text = text.replace(variableEntry.getKey(), variableEntry.getValue());
        }

        return text;
    }
}
