package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.exceptions.InputNumberNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/05/23 创建!
 * Created by Hykilpikonna on 2018/05/23!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data @AllArgsConstructor
public class MLFingersMove
{
    public FingersPlayerType playerType;
    public FingersSituation lastSituation; // 移动之前的局面
    public FingersSituation currentSituation; // 移动之后的局面
    public int moveFrom;
    public int moveTo;

    /**
     * 获取下一步对象
     * @param playerType 玩家
     * @param lastSituation 上一步的局面
     * @param moveFrom 自己手上的数字
     * @param moveTo 对面手上的数字
     * @return 下一步的对象
     * @throws InputNumberNotFoundException 输入数字不在手上
     */
    public static MLFingersMove nextMove(FingersPlayerType playerType, FingersSituation lastSituation, int moveFrom, int moveTo) throws InputNumberNotFoundException
    {
        int playerHandIndex = lastSituation.findPlayerIndex(playerType == FingersPlayerType.Player ? moveFrom : moveTo);
        int botHandIndex = lastSituation.findBotIndex(playerType == FingersPlayerType.Bot ? moveFrom : moveTo);

        FingersSituation newSituation = lastSituation.createDuplicate();
        if (playerType == FingersPlayerType.Player) newSituation.getPlayerHand()[playerHandIndex] = (moveFrom + moveTo) % 10;
        if (playerType == FingersPlayerType.Bot) newSituation.getBotHand()[botHandIndex] = (moveFrom + moveTo) % 10;

        return new MLFingersMove(playerType, lastSituation, newSituation, moveFrom, moveTo);
    }

    @Override
    public String toString()
    {
        return String.format("S%s.M%s%s",
                lastSituation.toString(),
                moveFrom,
                moveTo
        );
    }

    /**
     * 判断1v1胜败结果 封装
     * @return 1v1胜败结果
     */
    public FingersPlayerType get1v1Result()
    {
        return get1v1Result(currentSituation.playerHand[0], currentSituation.botHand[0], playerType.getTheOther());
    }

    /**
     * 判断1v1胜败结果
     * @param playerHand 玩家手上的数
     * @param botHand 机器人手上的数
     * @param whosTurn 该谁走
     * @return 对于数组第一个数表示的玩家的胜败
     */
    public static FingersPlayerType get1v1Result(int playerHand, int botHand, FingersPlayerType whosTurn)
    {
        if (botHand == 1 && playerHand == 3) return null;
        if (botHand == 3 && playerHand == 1) return null;
        if (botHand == 2 && playerHand == 6) return null;
        if (botHand == 6 && playerHand == 2) return null;

        if (botHand == 0) return FingersPlayerType.Bot;
        if (playerHand == 0) return FingersPlayerType.Player;

        if (whosTurn == FingersPlayerType.Player) playerHand = (playerHand + botHand) % 10;
        else botHand = (playerHand + botHand) % 10;

        return get1v1Result(playerHand, botHand, whosTurn.getTheOther());
    }
}
