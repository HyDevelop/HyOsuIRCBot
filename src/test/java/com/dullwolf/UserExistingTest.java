package com.dullwolf;

import cc.moecraft.logger.DebugLogger;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import cc.moecraft.irc.osubot.utils.ReflectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import static java.lang.System.in;

/**
 * 此类由 Hykilpikonna 在 2018/04/26 创建!
 * Created by Hykilpikonna on 2018/04/26!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class UserExistingTest
{
    private static DebugLogger logger = new DebugLogger("UserExistingTest", true);

    private static String defaultKey = PropertiesUtil.readKey("osu_key");

    public static void main(String[] args) throws IllegalAccessException, IOException, InvocationTargetException, InstantiationException
    {
        logger.log("当前测试OsuTrack的API");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        logger.log("输入APIKey (输入def既用默认): ");

        String apiKey = reader.readLine();

        OsuAPIUtils utils = new OsuAPIUtils(apiKey.equalsIgnoreCase("def") ? defaultKey : apiKey, new DownloadUtils(5000));

        logger.log("输入用户名: ");

        String username = reader.readLine();

        logger.log("用户存在: " + utils.isUserExisting(username));

        UserData userData = null;
        try {
            userData = (UserData) utils.get(UserParameters.builder().u(username).build()).get(0);
        } catch (JsonEmptyException | RequiredParamIsNullException e) {
            e.printStackTrace();
        }

        // 输出所有值
        logger.debug("");
        logger.debug("获取到的信息: ");
        ReflectUtils.printAllValue(userData);
    }
}
