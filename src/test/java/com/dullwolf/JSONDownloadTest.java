package com.dullwolf;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.utils.DownloadUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class JSONDownloadTest
{
    private static DebugLogger logger = new DebugLogger("Test", true);
    public static void main(String[] args) throws MalformedURLException
    {
        logger.debug(DownloadUtils.downloadAsString(new URL("https://ameobea.me/osutrack/api/get_changes.php?user=hykilpikonna&mode=0"), 5000));
        logger.debug(DownloadUtils.getJSONObjectFromURL("https://ameobea.me/osutrack/api/get_changes.php?user=hykilpikonna&mode=0", 5000).toString());
    }
}
