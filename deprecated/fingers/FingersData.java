package cc.moecraft.irc.osubot.minigames.fingers;

import cc.moecraft.irc.osubot.minigames.fingers.exceptions.InputNumberNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data @AllArgsConstructor
public class FingersData
{
    // TODO: 排序
    public FingersData(int playerHand1, int playerHand2, int botHand1, int botHand2)
    {
        playerHand[0] = playerHand1;
        playerHand[1] = playerHand2;

        botHand[0] = botHand1;
        botHand[1] = botHand2;
    }

    public int[] playerHand = new int[2];
    public int[] botHand = new int[2];

    /**
     * 找到数值对应的玩家的手的编号
     * @param value 数值
     * @return 手
     * @throws InputNumberNotFoundException 没找到
     */
    public int findPlayerIndex(int value) throws InputNumberNotFoundException
    {
        if (playerHand[0] == value) return 0;
        if (playerHand[1] == value) return 1;
        throw new InputNumberNotFoundException(InputNumberNotFoundException.Type.PLAYER);
    }

    /**
     * 找到数值对应的机器人的手的编号
     * @param value 数值
     * @return 手
     * @throws InputNumberNotFoundException 没找到
     */
    public int findBotIndex(int value) throws InputNumberNotFoundException
    {
        if (botHand[0] == value) return 0;
        if (botHand[1] == value) return 1;
        throw new InputNumberNotFoundException(InputNumberNotFoundException.Type.BOT);
    }

    /**
     * 验证这组数据是否合理
     * @return 是否合理
     */
    public boolean isValid()
    {
        return isValidNumber(playerHand[0]) && isValidNumber(playerHand[1]) && isValidNumber(botHand[0]) && isValidNumber(botHand[1]);
    }

    /**
     * 验证一个数字是否合理 ( 在1-9之间 )
     * @param number 数字
     * @return 是否合理
     */
    public static boolean isValidNumber(int number)
    {
        return number >= 0 && number <= 9;
    }

    /**
     * 例子: 1111
     * @return 字符串形式的这个数据组
     */
    @Override
    public String toString()
    {
        return playerHand[0] + "" + playerHand[1] + "" + botHand[0] + "" + botHand[1];
    }

    /**
     * 复制一份对象
     * @return 新的同样值的对象
     */
    public FingersData createDuplicate()
    {
        return new FingersData(playerHand[0], playerHand[1], botHand[0], botHand[1]);
    }
}
