package cc.moecraft.test.html;

import cc.moecraft.irc.osubot.osu.OsuHtmlUtils;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.logger.DebugLogger;
import cc.moecraft.scripts.AchievementClassGenerator;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 此类由 Hykilpikonna 在 2018/05/12 创建!
 * Created by Hykilpikonna on 2018/05/12!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class GetHaitaiDataTest
{
    private static DebugLogger logger = new DebugLogger("GetHaitaiDataTest", true);

    public static void main(String[] args) throws Exception
    {
        OsuHtmlUtils osuHtmlUtils = new OsuHtmlUtils(new DownloadUtils(5000));

        HashMap<String, String> haitaiMap = AchievementClassGenerator.getHaitaiData(osuHtmlUtils, "dont-let-the-bunny-distract-you", new ArrayList<>(Collections.singletonList("<div id=\"solution\">")));

        logger.debug("HaitaiMap = " + haitaiMap);
    }
}
