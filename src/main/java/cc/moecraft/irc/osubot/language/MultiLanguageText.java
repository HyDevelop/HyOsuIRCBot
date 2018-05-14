package cc.moecraft.irc.osubot.language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/05/14 创建!
 * Created by Hykilpikonna on 2018/05/14!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class MultiLanguageText
{
    @NotNull
    private final String text;
    private Type type = Type.DIRECT_TEXT;
    private Map<String, String> args = new HashMap<>();

    /**
     * 多语言对象
     * @param text 消息 / 语言节点
     * @param type 类型 ( 决定上面到底是消息还是语言节点还是空 )
     */
    public MultiLanguageText(String text, Type type)
    {
        this.text = text;
        this.type = type;
    }

    public enum Type
    {
        LANGUAGE_NODE,  // 语言节点形式
        DIRECT_TEXT,    // 消息形式
        EMPTY           // 空的
    }

    /**
     * @return 一个空的多语言消息实例
     */
    public static MultiLanguageText empty()
    {
        return new MultiLanguageText("", Type.EMPTY);
    }

    /**
     * 用语言节点创建一个多语言消息实例
     * @param languageNode 语言节点
     * @return 多语言实例
     */
    public static MultiLanguageText languageNode(String languageNode)
    {
        return new MultiLanguageText(languageNode, Type.LANGUAGE_NODE);
    }

    /**
     * 创建一个消息多语言实例 ( 这样的话其实不是多语言, 而是固定的 )
     * @param text 消息
     * @return 多语言实例
     */
    public static MultiLanguageText directText(String text)
    {
        return new MultiLanguageText(text, Type.DIRECT_TEXT);
    }
}
