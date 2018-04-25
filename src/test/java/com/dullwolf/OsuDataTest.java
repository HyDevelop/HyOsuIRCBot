package com.dullwolf;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.parameters.OsuTrackParameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

/**
 * 此类由 Hykilpikonna 在 2018/04/25 创建!
 * Created by Hykilpikonna on 2018/04/25!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class OsuDataTest
{
    private static DebugLogger logger = new DebugLogger("OsuDataTest", true);

    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        logger.log("输入APIKey: ");

        String apiKey = reader.readLine();

        OsuAPIUtils utils = new OsuAPIUtils(apiKey);

        utils.get(new OsuTrackParameters("Hykilpikonna", 0));
    }
}
