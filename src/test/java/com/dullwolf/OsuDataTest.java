package com.dullwolf;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.parameters.OsuTrackParameters;
import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.InputUtils;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import com.mysql.fabric.xmlrpc.base.Param;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    private static String defaultKey = PropertiesUtil.readKey("osu_key");

    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        logger.log("输入APIKey (输入def既用默认): ");

        String apiKey = reader.readLine();

        OsuAPIUtils utils = new OsuAPIUtils(apiKey.equalsIgnoreCase("def") ? defaultKey : apiKey, new DownloadUtils(5000));

        logger.log("输入要测试获取的东西 ( Osu玩家 = 1, OsuTrack玩家 = 2 ): ");

        String thingToTest = reader.readLine();

        ParametersBase parameters;

        switch (thingToTest)
        {
            case "1":
                parameters = new UserParameters();
                break;
            case "2":
                parameters = new OsuTrackParameters();
                break;
            default:
                return;
        }

        parameters = InputUtils.inputAllParams(parameters);

        logger.debug("当前信息: ");
        ReflectUtils.printAllValue(parameters);

        DataBase dataBase = utils.get(parameters).get(0);

        // 输出所有值
        logger.debug("");
        logger.debug("获取到的信息: ");
        ReflectUtils.printAllValue(dataBase);
    }
}
