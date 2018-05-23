package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.DataSetInvalidException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.GameEndedException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.InputNumberNotFoundException;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.PlayerInputInvalidException;

import java.util.HashMap;
import java.util.Map;

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
    public static FingersDatabase database = new FingersDatabase();

    /**
     * 通过当前局面和获取到的玩家移动来计算玩家移动后的局面
     * @return 玩家移动后的局面
     */
    public static FingersData applyPlayerMove(FingersData current, int fromPlayerFinger, int toBotFinger)
            throws DataSetInvalidException, PlayerInputInvalidException, InputNumberNotFoundException, GameEndedException
    {
        // 判断数字是否为1到9
        if (!current.isValid()) throw new DataSetInvalidException();
        if (!(FingersData.isValidNumber(fromPlayerFinger) && FingersData.isValidNumber(toBotFinger))) throw new PlayerInputInvalidException();

        // 判断游戏是否已经结束
        if (current.getPlayerHand()[0] == 0 && current.getPlayerHand()[1] == 0) throw new GameEndedException(GameEndedException.WinnerType.PLAYER);
        if (current.getBotHand()   [0] == 0 && current.getBotHand()   [1] == 0) throw new GameEndedException(GameEndedException.WinnerType.BOT);

        // 获取玩家移动后的局面
        return getDataAfterMove(current, fromPlayerFinger, toBotFinger, false);
    }

    /**
     * 获取移动之后的局面数据
     * @param old 移动之前的局面数据
     * @param moveFrom 移动者的数
     * @param moveTo 对面的数
     * @param botMove 移动者是不是机器人
     * @return 移动之后的局面数据
     */
    public static FingersData getDataAfterMove(FingersData old, int moveFrom, int moveTo, boolean botMove) throws InputNumberNotFoundException
    {
        if (botMove)
        {
            int playerHandIndex = old.findPlayerIndex(moveTo);
            int botHandIndex = old.findBotIndex(moveFrom);

            FingersData result = old.createDuplicate();
            result.getBotHand()[botHandIndex] = (moveFrom + moveTo) % 10;

            return result;
        }
        else
        {
            int playerHandIndex = old.findPlayerIndex(moveFrom);
            int botHandIndex = old.findBotIndex(moveTo);

            FingersData result = old.createDuplicate();
            result.getPlayerHand()[playerHandIndex] = (moveFrom + moveTo) % 10;

            return result;
        }
    }

    /**
     * 计算胜率最高的一步 封装
     * @return 胜率最高的一步
     */
    public static int[] calculateBestMove(FingersData data)
    {
        // 数据库记录过了, 直接返回
        if (database.containsBestMoveData(data))
        {
            FingersWinRatioData bestMove = database.getBestMoveData(data);

            return new int[]{bestMove.getBotMoveData().getFromBotFinger(), bestMove.getBotMoveData().getToPlayerFinger()};
        }

        int zeroes = 0;

        if (data.playerHand[1] == 0) zeroes++;
        if (data.botHand   [1] == 0) zeroes++;

        // 如果是1v1
        if (zeroes == 2)
        {
            return new int[]{data.botHand[0], data.playerHand[0]};
        }
        else // 如果是2v1 / 2v2
        {
            // 列出所有可能的移动
            // 如果局面是 3281, 这里就有 [3, 8], [3, 1]. [2, 8], [2, 1]
            // 如果局面是 3280, 这里就有 [3, 8], [2, 8]
            Map<Integer, Integer> allPossibilities = new HashMap<>();

            for (int botHandMove : data.getBotHand())
            {
                for (int playerHandMove : data.getPlayerHand())
                {
                    if (botHandMove != 0 && playerHandMove != 0) allPossibilities.put(botHandMove, playerHandMove);
                }
            }

            FingersWinRatioData result = new FingersWinRatioData(null, 0, 0, 0);
            FingersWinRatioData max = new FingersWinRatioData(null, 0, 0, 0);

            // 循环每个
            for (Map.Entry<Integer, Integer> possibility : allPossibilities.entrySet())
            {
                try
                {
                    FingersWinRatioData subResult = calculateBestMove(
                            new FingersBotMoveData(
                                    getDataAfterMove(
                                            data,
                                            possibility.getKey(),
                                            possibility.getValue(),
                                            true),
                                    possibility.getKey(),
                                    possibility.getValue()),
                            false,
                            0);

                    // 把每个的胜/败/循环数统计起来
                    result.setWin(result.getWin() + subResult.getWin());
                    result.setLose(result.getLose() + subResult.getLose());
                    result.setDraw(result.getDraw() + subResult.getLose());

                    if (subResult.getRatioInPercentage() >= max.getRatioInPercentage()) max = subResult;
                }
                catch (InputNumberNotFoundException e)
                {
                    // 理论上这里永远不会发生
                    e.printStackTrace();
                }
            }

            // 计算当前胜率最高的一步记录数据库
            result.setBotMoveData(max.getBotMoveData());
            database.setBestMoveData(result);

            return new int[]{result.getBotMoveData().getData().botHand[0], result.getBotMoveData().getData().playerHand[0]};
        }
    }

    /**
     * 计算胜率最高的一步 递归
     * @param last 递归上层的数据
     * @param botMove 是不是该机器人移动
     * @param count 递归第几层 ( 用来Debug的, 没有实际用途 )
     * @return 局面, 胜数, 败数, 循环数
     */
    public static FingersWinRatioData calculateBestMove(FingersBotMoveData last, boolean botMove, int count)
    {
        // 数据库记录过了, 直接返回
        if (database.containsBestMoveData(last.getData()))
            return database.getBestMoveData(last.getData());

        int zeroes = 0;

        if (last.getData().playerHand[1] == 0) zeroes++;
        if (last.getData().botHand   [1] == 0) zeroes++;

        // 如果是1v1
        if (zeroes == 2)
        {
            FingersGameResult _1v1Result = calculate1v1Result(new int[]{last.getData().botHand[1], last.getData().playerHand[1]}, botMove ? 0 : 1);

            FingersWinRatioData result = null;

            if (_1v1Result == FingersGameResult.WIN) result = new FingersWinRatioData(last, 1, 0, 0);
            if (_1v1Result == FingersGameResult.LOSE) result = new FingersWinRatioData(last, 0, 1, 0);
            if (_1v1Result == FingersGameResult.DRAW) result = new FingersWinRatioData(last, 0, 0, 1);

            database.setBestMoveData(result);
            return result;
        }
        else // 如果是2v1 / 2v2
        {
            // 列出所有可能的移动
            // 如果局面是 3281, 这里就有 [3, 8], [3, 1]. [2, 8], [2, 1]
            // 如果局面是 3280, 这里就有 [3, 8], [2, 8]
            Map<Integer, Integer> allPossibilities = new HashMap<>();

            for (int botHandMove : last.getData().getBotHand())
            {
                for (int playerHandMove : last.getData().getPlayerHand())
                {
                    if (botHandMove != 0 && playerHandMove != 0) allPossibilities.put(botHandMove, playerHandMove);
                }
            }

            FingersWinRatioData result = new FingersWinRatioData(last, 0, 0, 0);
            FingersWinRatioData max = new FingersWinRatioData(null, 0, 0, 0);

            // 循环每个
            for (Map.Entry<Integer, Integer> possibility : allPossibilities.entrySet())
            {
                try
                {
                    FingersWinRatioData subResult = calculateBestMove(
                            new FingersBotMoveData(
                                    getDataAfterMove(
                                            last.getData(),
                                            possibility.getKey(),
                                            possibility.getValue(),
                                            botMove),
                                    possibility.getKey(),
                                    possibility.getValue()),
                            !botMove,
                            ++count);

                    // 把每个的胜/败/循环数统计起来
                    result.setWin(result.getWin() + subResult.getWin());
                    result.setLose(result.getLose() + subResult.getLose());
                    result.setDraw(result.getDraw() + subResult.getLose());

                    if (subResult.getRatioInPercentage() >= max.getRatioInPercentage()) max = subResult;
                }
                catch (InputNumberNotFoundException e)
                {
                    // 理论上这里永远不会发生
                    e.printStackTrace();
                }
            }

            // 计算当前胜率最高的一步记录数据库
            result.setBotMoveData(max.getBotMoveData());
            database.setBestMoveData(result);

            return result;
        }
    }

    /**
     * 判断1v1胜败结果
     * @param hands 两个玩家的数 例子: [7, 4]
     * @param indexMove 该谁走, 0 或者 1
     * @return 对于数组第一个数表示的玩家的胜败
     */
    public static FingersGameResult calculate1v1Result(int[] hands, int indexMove)
    {
        if (hands[0] == 1 && hands[1] == 3) return FingersGameResult.DRAW;
        if (hands[0] == 3 && hands[1] == 1) return FingersGameResult.DRAW;
        if (hands[0] == 2 && hands[1] == 6) return FingersGameResult.DRAW;
        if (hands[0] == 6 && hands[1] == 2) return FingersGameResult.DRAW;

        if (hands[0] == 0) return FingersGameResult.WIN;
        if (hands[1] == 0) return FingersGameResult.LOSE;

        int other = indexMove == 0 ? 1 : 0;

        hands[indexMove] = (hands[indexMove] + hands[other]) % 10;

        return calculate1v1Result(hands, other);
    }
}
