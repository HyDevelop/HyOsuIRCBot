package cc.moecraft.consoleprograms;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersPlayerType;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.MLFingersAI;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.MLFingersGame;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.MLFingersMove;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.GameEndedException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.InputNumberNotFoundException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.NotYourTurnException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.PlayerInputInvalidException;
import cc.moecraft.logger.AnsiColor;
import cc.moecraft.logger.DebugLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static cc.moecraft.logger.AnsiColor.RESET;
import static cc.moecraft.logger.AnsiColor.YELLOW;
import static java.lang.System.in;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class FingersBotPlayWithItself
{
    private static DebugLogger logger = new DebugLogger("FingersGame", true);

    public static void main(String[] args) throws IOException
    {
        byte multiple = 1;

        if (multiple == 0) startOneGame(true);
        else while (true) startOneGame(false);
    }

    public static void startOneGame(boolean logging)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        MLFingersGame game = new MLFingersGame(MLFingersAI.getDatabase());
        long time = System.currentTimeMillis();

        try
        {
            if (logging) logger.log("开始游戏, 机器人和自己打");

            while (true)
            {
                if (game.getMoves().size() > 10000) new GameEndedException(null, game).end();

                try
                {
                    MLFingersMove botMove = MLFingersAI.makeTheNextMove(game, FingersPlayerType.Player);

                    if (logging) logger.log("局面: " + YELLOW + game.getLastMove().getLastSituation().toString() + RESET +
                            ", Bot 1: " + YELLOW + botMove.getMoveFrom() + " " + botMove.getMoveTo() + RESET +
                            (MLFingersAI.getDatabase().containsWR(botMove) ? ", 这一步的胜率: " + YELLOW + MLFingersAI.getDatabase().getWR(botMove).getRatioInPercentage() + RESET : ""));
                }
                catch (NotYourTurnException | PlayerInputInvalidException e)
                {
                    // 不可能发生的情况
                    e.printStackTrace();
                }

                try
                {
                    MLFingersMove botMove = MLFingersAI.makeTheNextMove(game, FingersPlayerType.Bot);

                    if (logging) logger.log("局面: " + YELLOW + game.getLastMove().getLastSituation().toString() + RESET +
                            ", Bot 1: " + YELLOW + botMove.getMoveFrom() + " " + botMove.getMoveTo() + RESET +
                            (MLFingersAI.getDatabase().containsWR(botMove) ? ", 这一步的胜率: " + YELLOW + MLFingersAI.getDatabase().getWR(botMove).getRatioInPercentage() + RESET : ""));
                }
                catch (NotYourTurnException | PlayerInputInvalidException e)
                {
                    // 不可能发生的情况
                    e.printStackTrace();
                }
            }
        }
        catch (GameEndedException e)
        {
            if (logging)
            {
                if (e.getWinner() == null) logger.log("接下来继续玩的话会死循环, 算作平局结束了游戏");
                else logger.log("游戏已经结束. " + (e.getWinner() == FingersPlayerType.Player ? "Bot 1" : "Bot 2") + "胜利!");

                logger.log("总耗时: " + (System.currentTimeMillis() - time) + "毫秒");
            }
            else
            {
                logger.log("一局结束了, 获胜者: " + (e.getWinner() == null ? "死循环" : e.getWinner()) + ", 耗时: " + (System.currentTimeMillis() - time));
            }
        }
    }
}
