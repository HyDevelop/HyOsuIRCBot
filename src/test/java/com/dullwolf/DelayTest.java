package com.dullwolf;

import cc.moecraft.logger.DebugLogger;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;

/**
 * 此类由 Hykilpikonna 在 2018/04/26 创建!
 * Created by Hykilpikonna on 2018/04/26!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class DelayTest
{
    private static DebugLogger logger = new DebugLogger("DelayTest", true);

    public static void main(String[] args)
    {
        long startingTime = System.currentTimeMillis();

        logger.log(PropertiesUtil.readKey("osu_key"));

        logger.log("获取Key完成, 用时: " + (System.currentTimeMillis() - startingTime));
    }
}
