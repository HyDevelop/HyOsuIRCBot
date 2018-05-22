package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data
@AllArgsConstructor
public class FingersData
{
    public int playerHand1;
    public int playerHand2;

    public int botHand1;
    public int botHand2;

    /**
     * 验证这组数据是否合理
     * @return 是否合理
     */
    public boolean isValid()
    {
        return isValidNumber(playerHand1) && isValidNumber(playerHand2) && isValidNumber(botHand1) && isValidNumber(botHand2);
    }

    /**
     * 验证一个数字是否合理 ( 在1-9之间 )
     * @param number 数字
     * @return 是否合理
     */
    private boolean isValidNumber(int number)
    {
        return number >= 1 && number <= 9;
    }
}
