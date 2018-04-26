package com.dullwolf;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.parameters.OsuTrackParameters;
import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import cc.moecraft.irc.osubot.utils.*;
import com.google.gson.JsonElement;
import com.jfinal.core.paragetter.Para;

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
public class GSONTest
{
    private static DebugLogger logger = new DebugLogger("GSONTest", true);

    private static String defaultKey = PropertiesUtil.readKey("osu_key");

    public static void main(String[] args) throws IllegalAccessException, IOException, InvocationTargetException
    {
        logger.log("当前测试OsuTrack的API");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        logger.log("输入APIKey (输入def既用默认): ");

        String apiKey = reader.readLine();

        OsuAPIUtils utils = new OsuAPIUtils(apiKey.equalsIgnoreCase("def") ? defaultKey : apiKey, new DownloadUtils(5000));

        ParametersBase parameters = new OsuTrackParameters();

        parameters = InputUtils.inputAllParams(parameters);

        logger.debug("当前信息: ");
        ReflectUtils.printAllValue(parameters);

        JsonElement jsonElement = utils.getJsonElementFromParameter(parameters);

        logger.log(" is Array: " + jsonElement.isJsonArray());
        logger.log(" is Object: " + jsonElement.isJsonObject());

        // 失败: logger.log(" Object 强制获取 Array: " + jsonElement.getAsJsonArray().toString());

        logger.log(" Object 转换 Array: " + JsonUtils.toJsonArray(jsonElement.getAsJsonObject()).toString());

        // 输出所有值
        logger.debug("");
        logger.debug("获取到的信息: ");
        //ReflectUtils.printAllValue(dataBase);
    }
}
