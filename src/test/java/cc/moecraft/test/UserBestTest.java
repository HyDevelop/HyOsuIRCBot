package cc.moecraft.test;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.commands.osu.CommandRecent;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuAPIWrapper;
import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.data.OsuTrackData;
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

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class UserBestTest
{
    private static DebugLogger logger = new DebugLogger("Test", true);

    public static void main(String[] args) throws IllegalAccessException, RequiredParamIsNullException, MalformedURLException, JsonEmptyException {
        Main.setLogger(logger);

        DownloadUtils downloader = new DownloadUtils(5000);
        OsuAPIUtils osuAPIUtils = new OsuAPIUtils(PropertiesUtil.readKey("osu_key"), downloader);
        OsuAPIWrapper wrapper = new OsuAPIWrapper(osuAPIUtils);

        OsuTrackData data = wrapper.getProgressByDays("dullwolf", 0, "string", 29);

        ReflectUtils.printAllValue(data);

        /*ReflectUtils.roundAllNumbers(data, 1);

        String format = "[osu://b/%beatmap_id% [%cm%: %artist% - %title% (%version%)]]: ★ %difficultyrating% | 成绩: %rank% | %ppmsg% | %ca%% | %cscore% | %maxcombo%x/%max_combo%x 连击";

        format = ReflectUtils.replaceReflectVariables(data, format, false, true);

        logger.log(format);*/
    }
}
