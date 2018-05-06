package cc.moecraft.test;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.commands.osu.CommandRecent;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuAPIWrapper;
import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.data.UserRecentData;
import cc.moecraft.irc.osubot.osu.data.UserScoreData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RecentScoreNotEnough;
import cc.moecraft.irc.osubot.osu.exceptions.RelatedScoreNotFoundException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.logger.DebugLogger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class OsuDataTest
{
    private static DebugLogger logger = new DebugLogger("Test", true);

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, RequiredParamIsNullException, MalformedURLException, JsonEmptyException, RecentScoreNotEnough {
        Main.setLogger(logger);

        DownloadUtils downloader = new DownloadUtils(5000);
        OsuAPIUtils osuAPIUtils = new OsuAPIUtils(PropertiesUtil.readKey("osu_key"), downloader);
        OsuAPIWrapper wrapper = new OsuAPIWrapper(osuAPIUtils);


        CommandRecent.UsernameAndIndexAndMode info = new CommandRecent.UsernameAndIndexAndMode(1, 0, "nekomimi_kyara");

        UserRecentData data = wrapper.getRecent(info);

        BeatmapData beatmapData = wrapper.getBeatmap(data);

        String ppMsg;

        try {
            UserScoreData scoreData = wrapper.getScore(info, data);

            if (scoreData.getPp() == null)
                ppMsg = "未计分!";
            else
                ppMsg = String.valueOf(Math.round(scoreData.getPp() * 100d) / 100d) + "pp";
        } catch (RelatedScoreNotFoundException e) {
            ppMsg = "未计分!";
        }

        ReflectUtils.roundAllNumbers(data, 1);

        String format = "[osu://b/%beatmap_id% [%cm%: %artist% - %title% (%version%)]]: ★ %difficultyrating% | 成绩: %rank% | %ppmsg% | %ca%% | %cscore% | %maxcombo%x/%max_combo%x 连击";

        format = ReflectUtils.replaceReflectVariables(data, format, false, true);
        format = ReflectUtils.replaceReflectVariables(beatmapData, format, false, true);
        format = format.replace("%cm%", OsuAPIUtils.getModeNameWithMode(beatmapData.getMode()));
        format = format.replace("%ca%", String.valueOf(Math.round(data.getAcc(wrapper, beatmapData.getMode()) * 10000d) / 100d));
        format = format.replace("%cscore%", new DecimalFormat("#,###").format(Math.round(data.getScore())));
        format = format.replace("%ppmsg%", ppMsg);

        logger.log(format);
    }
}
