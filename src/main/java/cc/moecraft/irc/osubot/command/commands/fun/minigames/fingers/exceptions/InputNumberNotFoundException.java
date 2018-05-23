package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersPlayerType;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersSituation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class InputNumberNotFoundException extends Exception
{
    private FingersPlayerType type;
    private FingersSituation situation;
    private int move;
}
