package cc.moecraft.consoleprograms;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersPlayerType;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.MLFingersAI;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.MLFingersGame;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.MLFingersMove;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.GameEndedException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.InputNumberNotFoundException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.NotYourTurnException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.PlayerInputInvalidException;
import cc.moecraft.logger.DebugLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        MLFingersGame game = new MLFingersGame(MLFingersAI.getDatabase());
        long time = System.currentTimeMillis();

        try
        {
            logger.log("开始游戏, 机器人和自己打");

            while (true)
            {
                try
                {
                    MLFingersMove botMove = MLFingersAI.makeTheNextMove(game, FingersPlayerType.Player);

                    logger.log("Bot 1: " + botMove.getMoveFrom() + " " + botMove.getMoveTo() + ", 局面变成了: " + game.getLastMove().getCurrentSituation().toString());
                }
                catch (NotYourTurnException | PlayerInputInvalidException e)
                {
                    // 不可能发生的情况
                    e.printStackTrace();
                }

                try
                {
                    MLFingersMove botMove = MLFingersAI.makeTheNextMove(game, FingersPlayerType.Bot);

                    logger.log("Bot 2: " + botMove.getMoveFrom() + " " + botMove.getMoveTo() + ", 局面变成了: " + game.getLastMove().getCurrentSituation().toString());
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
            if (e.getWinner() == null) logger.log("游戏已经结束, 平局了");
            else logger.log("游戏已经结束. " + (e.getWinner() == FingersPlayerType.Player ? "Bot 1" : "Bot 2") + "胜利!");

            logger.log("总耗时: " + (System.currentTimeMillis() - time) + "毫秒");
        }
    }
}
