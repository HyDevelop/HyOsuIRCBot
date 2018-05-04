package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.BeatmapParameters;
import lombok.AllArgsConstructor;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
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
}
