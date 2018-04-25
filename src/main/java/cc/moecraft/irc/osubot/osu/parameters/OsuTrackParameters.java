package cc.moecraft.irc.osubot.osu.parameters;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.data.OsuTrackData;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class OsuTrackParameters extends ParametersBase
{
    @HttpParameter(required = true)
    private String user;

    @HttpParameter(required = true)
    private int mode;

    @Override
    public String subURL()
    {
        return "%COMPLETE_URL%https://ameobea.me/osutrack/api/get_changes.php";
    }

    @Override
    public DataBase dataStorageObject()
    {
        return new OsuTrackData();
    }
}
