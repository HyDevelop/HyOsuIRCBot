package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

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
public class MLFingersWinRatio
{
    public long win;
    public long lose;
    public long draw;

    public double getRatioInPercentage()
    {
        if (lose == 0) return win;

        return (double) win / (double) lose; // TODO: 优化这个算法, 加入Draw
    }
}
