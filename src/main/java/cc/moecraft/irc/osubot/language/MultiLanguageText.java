package cc.moecraft.irc.osubot.language;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;

/**
 * 此类由 Hykilpikonna 在 2018/05/14 创建!
 * Created by Hykilpikonna on 2018/05/14!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@RequiredArgsConstructor @AllArgsConstructor
public class MultiLanguageText
{
    @NotNull @Getter
    private final String text;
    @Getter
    private Type type = Type.DIRECT_TEXT;
    @Getter
    private Map<String, String> variables = new HashMap<>();

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
     * 添加/设置一个变量值
     *
     * 例子:
     *  set("username", "Hykilpikonna");
     *
     * 注意:
     *  后添加的变量值会覆盖先添加的变量值组
     *
     * @param variable 变量名
     * @param value 值
     */
    public void putVariable(String variable, String value)
    {
        variables.put(variable, value);
    }

    /**
     * 添加/设置一组变量值
     *
     * @param variables 变量值组
     */
    public void putVariables(Map<String, String> variables)
    {
        this.variables.putAll(variables);
    }

    /**
     * 添加/设置一组变量值
     *
     * @param pojo POJO对象
     */
    public void putVariables(Object pojo)
    {
        Field[] allFields = (Field[]) ArrayUtils.addAll(pojo.getClass().getDeclaredFields(), pojo.getClass().getFields());

        for (Field field : allFields)
        {
            field.setAccessible(true);

            try
            {
                putVariable("%" + field.getName().toLowerCase() + "%", field.get(pojo).toString());
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (NullPointerException ignored) {}
        }
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