package cc.moecraft.consoleprograms;

import cc.moecraft.irc.osubot.minigames.fingers.FingersPlayerType;
import cc.moecraft.irc.osubot.minigames.fingers.MLFingersAI;
import cc.moecraft.irc.osubot.minigames.fingers.MLFingersGame;
import cc.moecraft.irc.osubot.minigames.fingers.MLFingersMove;
import cc.moecraft.irc.osubot.minigames.fingers.exceptions.GameEndedException;
import cc.moecraft.irc.osubot.minigames.fingers.exceptions.InputNumberNotFoundException;
import cc.moecraft.irc.osubot.minigames.fingers.exceptions.NotYourTurnException;
import cc.moecraft.irc.osubot.minigames.fingers.exceptions.PlayerInputInvalidException;
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
 * Meow!
 *
 * @author Hykilpikonna
 */
public class FingersGame
{
    private static DebugLogger logger = new DebugLogger("FingersGame", true);

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        logger.log("谁先走? (Bot 或者 Player): ");

        FingersPlayerType first = FingersPlayerType.valueOf(reader.readLine());

        MLFingersGame game = new MLFingersGame(MLFingersAI.getDatabase(), first);

        try
        {
            while (true)
            {
                if (game.getLastMove().getPlayerType() == FingersPlayerType.Bot)
                {
                    logger.log("该你走: ");

                    String[] in = reader.readLine().replace(" ", "").split("");

                    try
                    {
                        MLFingersMove playerMove = MLFingersAI.playerMove(game, Integer.parseInt(in[0]), Integer.parseInt(in[1]));

                        logger.log("您移动了: " + playerMove.getMoveFrom() + " " + playerMove.getMoveTo());
                        logger.log("- 玩家手上数字: " + playerMove.getCurrentSituation().getPlayerHand()[0] + " " + playerMove.getCurrentSituation().getPlayerHand()[1]);
                        logger.log("- Bot 手上数字: " + playerMove.getCurrentSituation().getBotHand()[0] + " " + playerMove.getCurrentSituation().getBotHand()[1]);
                    }
                    catch (InputNumberNotFoundException e)
                    {
                        if (e.getType() == FingersPlayerType.Player)
                            logger.log("必须输入一个你手上有的数字, 您现在有: " + Arrays.toString(e.getSituation().getPlayerHand()) + ", 您输入的: " + e.getMove());
                        if (e.getType() == FingersPlayerType.Bot)
                            logger.log("必须输入一个机器人手上有的数字, 它现在有: " + Arrays.toString(e.getSituation().getBotHand()) + ", 您输入的: " + e.getMove());

                        continue;
                    }
                    catch (NotYourTurnException e)
                    {
                        // 不可能发生
                        e.printStackTrace();
                    }
                    catch (PlayerInputInvalidException e)
                    {
                        logger.log("您输入的数字 " + e.getInvalidInput() + " 不合理, 请重新输入 ( 必须为 1 到 9 )");

                        continue;
                    }
                    catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
                    {
                        logger.log("输入错误, 重新输入");

                        continue;
                    }
                }
                else
                {
                    try
                    {
                        MLFingersMove botMove = MLFingersAI.makeTheNextMove(game, FingersPlayerType.Bot);

                        logger.log("机器人移动了: " + botMove.getMoveFrom() + " " + botMove.getMoveTo());
                        logger.log("- 玩家手上数字: " + botMove.getCurrentSituation().getPlayerHand()[0] + " " + botMove.getCurrentSituation().getPlayerHand()[1]);
                        logger.log("- Bot 手上数字: " + botMove.getCurrentSituation().getBotHand()[0] + " " + botMove.getCurrentSituation().getBotHand()[1]);
                    }
                    catch (NotYourTurnException | PlayerInputInvalidException e)
                    {
                        // 不可能发生的情况
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (GameEndedException e)
        {
            if (e.getWinner() == null) logger.log("接下来继续玩的话会死循环, 算作平局结束了游戏");
            else logger.log("游戏已经结束. " + e.getWinner() + "胜利!");
        }
    }
}
