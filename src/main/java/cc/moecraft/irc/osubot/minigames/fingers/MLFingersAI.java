package cc.moecraft.irc.osubot.minigames.fingers;

import cc.moecraft.irc.osubot.minigames.fingers.exceptions.GameEndedException;
import cc.moecraft.irc.osubot.minigames.fingers.exceptions.InputNumberNotFoundException;
import cc.moecraft.irc.osubot.minigames.fingers.exceptions.NotYourTurnException;
import cc.moecraft.irc.osubot.minigames.fingers.exceptions.PlayerInputInvalidException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static cc.moecraft.irc.osubot.minigames.fingers.FingersPlayerType.Bot;
import static cc.moecraft.irc.osubot.minigames.fingers.FingersPlayerType.Player;

/**
 * 此类由 Hykilpikonna 在 2018/05/23 创建!
 * Created by Hykilpikonna on 2018/05/23!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class MLFingersAI
{
    @Getter
    private static MLFingersDatabase database = new MLFingersDatabase();

    /**
     * 让玩家走一步
     * @param game 游戏对象
     * @param moveFrom 玩家手上的数
     * @param moveTo 对面手上的数
     * @return 一步 ( 其实已经走过了 )
     * @throws InputNumberNotFoundException 输入无效
     * @throws NotYourTurnException 不该玩家走
     */
    public static MLFingersMove playerMove(MLFingersGame game, int moveFrom, int moveTo) throws InputNumberNotFoundException, NotYourTurnException, PlayerInputInvalidException, GameEndedException
    {
        return game.move(moveFrom, moveTo, Player);
    }

    /**
     * 让机器人走一步
     * @param game 游戏对象
     * @param playAs 以什么身份进行游戏
     * @return 一步 ( 其实已经走过了 )
     */
    public static MLFingersMove makeTheNextMove(MLFingersGame game, FingersPlayerType playAs) throws NotYourTurnException, PlayerInputInvalidException, GameEndedException
    {
        try // TODO: 必胜的可能性 死循环的可能性
        {
            // 记录方式: 两个数字 对应 一步的对象
            // 这样做是为了防止重复
            // 例子: [12, [[1, 1, 1, 1], 1, 2, Bot]]
            HashMap<String, MLFingersMove> possibilities = new HashMap<>();

            // 添加所有可能性
            for (int bot : game.getLastMove().getCurrentSituation().getBotHand())
                for (int player : game.getLastMove().getCurrentSituation().getPlayerHand())
                    if (bot != 0 && player != 0)
                        possibilities.put(bot + "" + player, MLFingersMove.nextMove(playAs, game.getLastMove().getCurrentSituation(),
                                playAs == Bot ? bot : player,
                                playAs == Bot ? player : bot));

            // 只有一种可能性的情况
            if (possibilities.size() == 1)
                return move(game, (MLFingersMove) ((Map.Entry) possibilities.entrySet().toArray()[0]).getValue());

            // 分开在数据库内的移动数据和不在的移动数据
            ArrayList<MLFingersMove> movesNotInDatabase = new ArrayList<>();
            HashMap<MLFingersMove, MLFingersWinRatio> movesInDatabase = new HashMap<>();

            // 从数据库内获取
            for (Map.Entry<String, MLFingersMove> entry : possibilities.entrySet())
            {
                if (database.containsWR(entry.getValue()))
                    movesInDatabase.put(entry.getValue(), database.getWR(entry.getValue()));
                else movesNotInDatabase.add(entry.getValue());
            }

            // 如果有数据库内没有记录的移动, 先试试
            if (movesNotInDatabase.size() > 0)
                return move(game, movesNotInDatabase.get(new Random().nextInt(movesNotInDatabase.size())));

            // 最大的输赢比例和最大输赢比例的移动
            MLFingersMove bestMove = (MLFingersMove) ((Map.Entry) movesInDatabase.entrySet().toArray()[0]).getKey();
            MLFingersWinRatio bestRatio = (MLFingersWinRatio) ((Map.Entry) movesInDatabase.entrySet().toArray()[0]).getValue();

            // 获取最大
            for (Map.Entry<MLFingersMove, MLFingersWinRatio> entry : movesInDatabase.entrySet())
            {
                if (entry.getValue().getRatioInPercentage() > bestRatio.getRatioInPercentage())
                {
                    bestMove = entry.getKey();
                    bestRatio = entry.getValue();
                }
            }

            return move(game, bestMove);
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
