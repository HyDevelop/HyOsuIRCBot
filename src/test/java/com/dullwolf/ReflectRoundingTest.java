package com.dullwolf;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import cc.moecraft.irc.osubot.utils.ReflectUtils;

import java.net.MalformedURLException;

/**
 * 此类由 Hykilpikonna 在 2018/04/27 创建!
 * Created by Hykilpikonna on 2018/04/27!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class ReflectRoundingTest
{
    private static DebugLogger logger = new DebugLogger("ReflectRoundingTest", true);

    public static void main(String[] args) throws IllegalAccessException, RequiredParamIsNullException, MalformedURLException, JsonEmptyException {
        DataBase userData = new OsuAPIUtils(PropertiesUtil.readKey("osu_key"), new DownloadUtils(5000)).get(UserParameters.builder().u("Hykilpikonna").build()).get(0);

        ReflectUtils.roundAllNumbers(userData, 1);

        logger.log(ReflectUtils.replaceReflectVariables(userData,
                "[%username% (%user_id%)]: %pp_raw%pp | lv.%level% | %accuracy%acc. | %count_rank_ssh%ssh | %count_rank_ss%ss |  %count_rank_sh%sh |  %count_rank_s%s |  %count_rank_a%a ",
                false, true));
    }
}
