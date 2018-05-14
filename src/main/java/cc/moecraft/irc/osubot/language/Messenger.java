package cc.moecraft.irc.osubot.language;

import cc.moecraft.irc.osubot.command.CommandManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import javax.annotation.security.DenyAll;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
@AllArgsConstructor @Data
public class Messenger
{
    private LanguageFileManager languageFileManager;
    private Map<String, String> globalVariables;

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
     * 解析多语言对象为字符串
     * @param multiLanguageText 多语言对象
     * @param lang 语言
     * @return 解析后的字符串
     */
    public String resolveMLT(MultiLanguageText multiLanguageText, String lang)
    {
        if (multiLanguageText.getType() == MultiLanguageText.Type.EMPTY) return "";

        String text;

        if (multiLanguageText.getType() == MultiLanguageText.Type.DIRECT_TEXT)
        {
            text = multiLanguageText.getText();
        }
        else // 是 LANGUAGE_NODE. 不用多判断一遍了
        {
            text = languageFileManager.get(lang, multiLanguageText.getText());
        }

        text = replaceVariables(text, globalVariables);
        text = replaceVariables(text, multiLanguageText.getVariables());

        return text;
        // return multiLanguageText.replace("%prefix%", CommandManager.getPrefix());
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
