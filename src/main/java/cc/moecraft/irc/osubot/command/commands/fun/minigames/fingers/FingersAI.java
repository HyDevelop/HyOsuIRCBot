package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.DataSetInvalidException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.GameEndedException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.InputNumberNotFoundException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.PlayerInputInvalidException;

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
    /**
     * 通过当前局面和获取到的玩家移动来计算玩家移动后的局面
     * @return 玩家移动后的局面
     */
    public static FingersData calculateNext(FingersData current, int fromPlayerFinger, int toBotFinger)
            throws DataSetInvalidException, PlayerInputInvalidException, InputNumberNotFoundException, GameEndedException
    {
        // 判断数字是否为1到9
        if (!current.isValid()) throw new DataSetInvalidException();
        if (!(FingersData.isValidNumber(fromPlayerFinger) && FingersData.isValidNumber(toBotFinger))) throw new PlayerInputInvalidException();

        // 判断游戏是否已经结束
        if (current.getPlayerHand()[1] == 0 && current.getPlayerHand()[2] == 0) throw new GameEndedException(GameEndedException.WinnerType.PLAYER);
        if (current.getBotHand()   [1] == 0 && current.getBotHand()   [2] == 0) throw new GameEndedException(GameEndedException.WinnerType.BOT);

        // 获取玩家移动后的局面
        int playerHandIndex = current.findPlayerIndex(fromPlayerFinger);
        int botHandIndex = current.findBotIndex(toBotFinger);

        current.getPlayerHand()[playerHandIndex] = (fromPlayerFinger + toBotFinger) % 10;

        return current;
    }
}
