package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.GameEndedException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.InputNumberNotFoundException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.NotYourTurnException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.PlayerInputInvalidException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersPlayerType.Bot;
import static cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersPlayerType.Player;

/**
 * 此类由 Hykilpikonna 在 2018/05/23 创建!
 * Created by Hykilpikonna on 2018/05/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class MLFingersAIRandom
{
    @Getter
    private static MLFingersDatabase database = new MLFingersDatabase();

    /**
     * 让随机机器人走一步
     * @param game 游戏对象
     * @param playAs 以什么身份进行游戏
     * @return 一步 ( 其实已经走过了 )
     */
    public static MLFingersMove makeTheNextMove(MLFingersGame game, FingersPlayerType playAs) throws NotYourTurnException, PlayerInputInvalidException, GameEndedException
    {
        // 这个是纯随机AI, 真正的AI在另外一个类
        try
        {
            HashMap<String, MLFingersMove> possibilities = new HashMap<>();

            // 添加所有可能性
            for (int bot : game.getLastMove().getCurrentSituation().getBotHand())
                for (int player : game.getLastMove().getCurrentSituation().getPlayerHand())
                    if (bot != 0 && player != 0)
                        possibilities.put(bot + "" + player, MLFingersMove.nextMove(playAs, game.getLastMove().getCurrentSituation(),
                                playAs == Bot ? bot : player,
                                playAs == Bot ? player : bot));

            ArrayList<MLFingersMove> possibilityList = new ArrayList<>();
            possibilities.forEach((k, v) -> possibilityList.add(v));

            // 直接随机
            return move(game, possibilityList.get(new Random().nextInt(possibilityList.size())));
        }
        catch (InputNumberNotFoundException e)
        {
            // 这种情况理论上永远不会发生
            e.printStackTrace();
            return null;
        }
    }

    private static MLFingersMove move(MLFingersGame game, MLFingersMove move) throws InputNumberNotFoundException, NotYourTurnException, PlayerInputInvalidException, GameEndedException
    {
        return game.move(move.getMoveFrom(), move.getMoveTo(), move.getPlayerType());
    }
}
