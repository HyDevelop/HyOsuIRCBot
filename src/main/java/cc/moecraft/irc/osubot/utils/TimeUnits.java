package cc.moecraft.irc.osubot.utils;

import lombok.Getter;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public enum TimeUnits
{
    Nano    (0, -1,     null),
    Millis  (1, 1000,   Nano),
    Second  (2, 1000,   Millis),
    Minute  (3, 60,     Second),
    Hour    (4, 60,     Minute),
    Day     (5, 24,     Hour),
    Week    (6, 7,      Day),
    Month   (7, 30,     Week);

    @Getter
    int index, multiplier; // 到上一个单位的乘数
    @Getter
    TimeUnits previous; // 哪个是上个单位

    TimeUnits(int index, int multiplier, TimeUnits previous)
    {
        this.index = index;
        this.multiplier = multiplier;
        this.previous = previous;
    }

    /**
     * 获取两个单位之间的乘数
     * @param unit1 单位1
     * @param unit2 单位2
     * @return 总乘数
     */
    public static double getTotalMultiplierBetweenTwoUnits(TimeUnits unit1, TimeUnits unit2)
    {
        if (unit1 == unit2) return 1;

        TimeUnits larger = unit1.getIndex() > unit2.getIndex() ? unit1 : unit2;
        TimeUnits smaller = unit1.getIndex() < unit2.getIndex() ? unit1 : unit2;

        boolean inverted = smaller == unit1;

        double totalMultiplier = 1;

        while (true)
        {
            if (larger == smaller) return inverted ? 1d / totalMultiplier : totalMultiplier;

            totalMultiplier *= larger.getMultiplier();
            larger = larger.getPrevious();
        }
    }
}
