package cc.moecraft.test.html;

import cc.moecraft.irc.osubot.osu.OsuHtmlUtils;
import cc.moecraft.irc.osubot.osu.data.AchievementData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.UnexpectedHtmlJsonException;
import cc.moecraft.irc.osubot.osu.exceptions.UserNotFoundException;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.logger.DebugLogger;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class OsuHtmlUtilsAchievementsTest
{
    private static DebugLogger logger = new DebugLogger("OsuHtmlUtilsAchievementsTest", true);

    public static void main(String[] args) throws UserNotFoundException, MalformedURLException, JsonEmptyException, UnexpectedHtmlJsonException
    {
        OsuHtmlUtils htmlUtils = new OsuHtmlUtils(new DownloadUtils(5000));

        ArrayList<AchievementData> data = htmlUtils.getAllAvailableAchievements();

        logger.debug("获取到的成就: " + data.toString());
    }
}
