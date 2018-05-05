package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.command.commands.osu.CommandRecent;
import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.data.UserRecentData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.BeatmapParameters;
import cc.moecraft.irc.osubot.osu.parameters.UserRecentParameters;
import lombok.AllArgsConstructor;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * OsuAPIUtils 的封装
 *
 * @author Hykilpikonna
 */
@AllArgsConstructor
public class OsuAPIWrapper
{
    OsuAPIUtils downloader;

    public ArrayList<BeatmapData> getBeatmap(BeatmapParameters parameters) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        ArrayList<BeatmapData> beatmapDataArrayList = new ArrayList<>();
        downloader.get(parameters).forEach(data -> beatmapDataArrayList.add((BeatmapData) data));
        return beatmapDataArrayList;
    }

    public ArrayList<UserRecentData> getRecent(UserRecentParameters parameters) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        ArrayList<UserRecentData> beatmapDataArrayList = new ArrayList<>();
        downloader.get(parameters).forEach(data -> beatmapDataArrayList.add((UserRecentData) data));
        return beatmapDataArrayList;
    }

    public UserRecentData getRecent(CommandRecent.UsernameAndIndexAndMode info) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        return getRecent(UserRecentParameters.builder().u(info.getUsername()).type("string").m(String.valueOf(info.getMode())).build()).get(info.getIndex());
    }
}
