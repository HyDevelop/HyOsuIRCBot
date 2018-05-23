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
}
