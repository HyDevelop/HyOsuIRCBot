package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/05/23 创建!
 * Created by Hykilpikonna on 2018/05/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
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

    @Override
    public String toString()
    {
        return String.format("%s/%s/%s", win, lose, draw);
    }

    /**
     * 从字符串转换 ( 字符串必须是toString那个格式的 )
     * @param stringValue 字符串
     * @return 胜率对象
     */
    public static MLFingersWinRatio parse(String stringValue)
    {
        String[] split = stringValue.split("/");

        return new MLFingersWinRatio(
                Long.parseLong(split[0]),
                Long.parseLong(split[1]),
                Long.parseLong(split[2])
        );
    }
}
