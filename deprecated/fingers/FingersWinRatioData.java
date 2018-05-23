package cc.moecraft.irc.osubot.minigames.fingers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data @AllArgsConstructor
public class FingersWinRatioData
{
    public FingersBotMoveData botMoveData;
    public BigDecimal win;
    public BigDecimal lose;
    public BigDecimal draw;

    public BigDecimal getRatioInPercentage()
    {
        if (lose.equals(BigDecimal.ZERO)) return win;

        return win.divide(lose, 2, RoundingMode.HALF_UP); // TODO: 优化这个算法, 加入Draw
    }
}
