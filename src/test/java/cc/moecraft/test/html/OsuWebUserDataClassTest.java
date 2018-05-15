package cc.moecraft.test.html;

import cc.moecraft.irc.osubot.osu.OsuHtmlUtils;
import cc.moecraft.irc.osubot.osu.data.web.WebsiteUserData;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.logger.DebugLogger;

/**
 * 此类由 Hykilpikonna 在 18-5-15 创建!
 * Created by Hykilpikonna on 18-5-15!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class OsuWebUserDataClassTest
{
    public DebugLogger debugLogger = new DebugLogger("OsuWebUserDataClassTest", true);

    public static void main(String[] args) throws IllegalAccessException
    {
        OsuHtmlUtils osuHtmlUtils = new OsuHtmlUtils(new DownloadUtils(5000));

        WebsiteUserData websiteUserData = osuHtmlUtils.getWebUserData("hykilpikonna");

        ReflectUtils.printAllValue(websiteUserData);
    }
}
