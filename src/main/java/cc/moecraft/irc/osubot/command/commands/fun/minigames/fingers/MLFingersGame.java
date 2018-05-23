package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.GameEndedException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.InputNumberNotFoundException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.NotYourTurnException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.PlayerInputInvalidException;
import lombok.Data;

import java.util.ArrayList;

import static cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersPlayerType.Bot;
import static cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersPlayerType.Player;

/**
 * 此类由 Hykilpikonna 在 2018/05/23 创建!
 * Created by Hykilpikonna on 2018/05/23!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data
public class MLFingersGame
{
    public ArrayList<MLFingersMove> moves;
    public MLFingersMove lastMove;
    public boolean ended = false;
    public MLFingersDatabase database;
}
