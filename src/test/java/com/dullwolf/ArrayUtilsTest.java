package com.dullwolf;

import cc.moecraft.logger.DebugLogger;
import cc.moecraft.irc.osubot.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2018/04/26 创建!
 * Created by Hykilpikonna on 2018/04/26!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class ArrayUtilsTest
{
    private static DebugLogger logger = new DebugLogger("ArrayUtilsTest", true);

    public static void main(String[] ignoredArgs)
    {
        ArrayList<String> args = new ArrayList<>(Arrays.asList("say", "hello", "world"));

        logger.log("Result: " + ArrayUtils.getTheRestArgsAsString(args, 1));
    }
}
