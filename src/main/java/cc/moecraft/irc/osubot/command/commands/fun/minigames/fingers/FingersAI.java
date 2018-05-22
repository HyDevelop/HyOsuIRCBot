package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.DataSetInvalidException;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class FingersAI
{
    public static FingersData calculateNext(FingersData current, int fromPlayerFinger, int toBotFinger) throws DataSetInvalidException
    {
        if (!current.isValid()) throw new DataSetInvalidException();

        return current; // TODO: 返回数据
    }
}
