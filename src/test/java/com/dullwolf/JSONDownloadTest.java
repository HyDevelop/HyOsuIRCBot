package com.dullwolf;

import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.logger.DebugLogger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class JSONDownloadTest
{
    private static DebugLogger logger = new DebugLogger("Test", true);
    public static void main(String[] args) throws MalformedURLException
    {
        logger.debug(DownloadUtils.downloadAsString(new URL("https://ameobea.me/osutrack/api/get_changes.php?user=hykilpikonna&mode=0"), 5000));
        logger.debug(Objects.requireNonNull(DownloadUtils.getJsonElementFromURL("https://ameobea.me/osutrack/api/get_changes.php?user=hykilpikonna&mode=0", 5000)).toString());
    }
}
