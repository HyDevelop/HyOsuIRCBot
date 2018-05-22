package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.DataSetInvalidException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.GameEndedException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.InputNumberNotFoundException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.PlayerInputInvalidException;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class FingersAI
{
    public static FingersData calculateNext(FingersData current, int fromPlayerFinger, int toBotFinger)
            throws DataSetInvalidException, PlayerInputInvalidException, InputNumberNotFoundException, GameEndedException
    {
        // 判断数字是否为1到9
        if (!current.isValid()) throw new DataSetInvalidException();
        if (!(FingersData.isValidNumber(fromPlayerFinger) && FingersData.isValidNumber(toBotFinger))) throw new PlayerInputInvalidException();

        // 判断游戏是否已经结束
        if (current.playerHand1 == 0 && current.playerHand2 == 0) throw new GameEndedException(GameEndedException.WinnerType.PLAYER);
        if (current.botHand1 == 0 && current.botHand2 == 0) throw new GameEndedException(GameEndedException.WinnerType.BOT);

        // 判断输入内容是否在玩家或者机器人手中
        if (current.playerHand1 != fromPlayerFinger && current.playerHand2 != fromPlayerFinger) throw new InputNumberNotFoundException(InputNumberNotFoundException.Type.PLAYER);
        if (current.botHand1 != toBotFinger && current.botHand2 != toBotFinger) throw new InputNumberNotFoundException(InputNumberNotFoundException.Type.BOT);

        return current; // TODO: 返回数据
    }
}
