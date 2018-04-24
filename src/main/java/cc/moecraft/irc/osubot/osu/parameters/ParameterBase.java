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
public abstract class ParameterBase
{
    public abstract String getSubURL();

    public abstract DataBase getDataStorage();
}
