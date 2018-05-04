package cc.moecraft.irc.osubot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class TimeUtils
{
    /**
     * 转换时间为字符串
     *
     * 时间格式和Date的格式一样
     *
     * @param format 字符串格式
     * @param time 时间
     * @param timeUnit 时间单位
     * @return 字符串
     */
    public static String convertToString(String format, float time, TimeUnits timeUnit)
    {
        long millisTime = Math.round(time * TimeUnits.getTotalMultiplierBetweenTwoUnits(timeUnit, TimeUnits.Millis));

        Date date = new Date(millisTime);
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
}
