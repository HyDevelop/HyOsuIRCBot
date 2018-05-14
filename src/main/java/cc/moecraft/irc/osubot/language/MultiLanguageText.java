package cc.moecraft.irc.osubot.language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 此类由 Hykilpikonna 在 2018/05/14 创建!
 * Created by Hykilpikonna on 2018/05/14!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class MultiLanguageText
{
    @NotNull
    private final String text;
    private Type type = Type.DIRECT_TEXT;

    public enum Type
    {
        LANGUAGE_NODE,  // 语言节点形式
        DIRECT_TEXT,    // 消息形式
        EMPTY           // 空的
    }
}
