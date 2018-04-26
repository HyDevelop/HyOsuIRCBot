package cc.moecraft.irc.osubot.osu.parameters;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
@Data
public abstract class ParametersBase
{
    public abstract String subURL();

    public abstract DataBase dataStorageObject();

    public abstract ReturnDataType returnDataType();

    /**
     * 获取的目标类型
     */
    public enum ReturnDataType
    {
        OBJECT, LIST, SINGLETON_LIST
    }
}
