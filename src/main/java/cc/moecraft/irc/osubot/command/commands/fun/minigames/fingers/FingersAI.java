package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.DataSetInvalidException;
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
    public static FingersData calculateNext(FingersData current, int fromPlayerFinger, int toBotFinger) throws DataSetInvalidException, PlayerInputInvalidException, InputNumberNotFoundException
    {
        if (!current.isValid()) throw new DataSetInvalidException();

        if (!(FingersData.isValidNumber(fromPlayerFinger) && FingersData.isValidNumber(toBotFinger))) throw new PlayerInputInvalidException();

        if (current.playerHand1 != fromPlayerFinger && current.playerHand2 != fromPlayerFinger) throw new InputNumberNotFoundException(InputNumberNotFoundException.Type.PLAYER);
        if (current.botHand1 != toBotFinger && current.botHand2 != toBotFinger) throw new InputNumberNotFoundException(InputNumberNotFoundException.Type.BOT);

        return current; // TODO: 返回数据
    }
}