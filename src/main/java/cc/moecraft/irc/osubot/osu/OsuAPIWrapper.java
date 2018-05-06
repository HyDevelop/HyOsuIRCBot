package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.command.commands.osu.CommandRecent;
import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.data.UserRecentData;
import cc.moecraft.irc.osubot.osu.data.UserScoreData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RecentScoreNotEnough;
import cc.moecraft.irc.osubot.osu.exceptions.RelatedScoreNotFoundException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.BeatmapParameters;
import cc.moecraft.irc.osubot.osu.parameters.UserRecentParameters;
import cc.moecraft.irc.osubot.osu.parameters.UserScoreParameters;
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

    // 谱面

    /**
     * 获取谱面组
     * 
     * @param parameters 谱面参数
     * @return 谱面组
     */
    public ArrayList<BeatmapData> getBeatmap(BeatmapParameters parameters) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        ArrayList<BeatmapData> beatmapDataArrayList = new ArrayList<>();
        downloader.get(parameters).forEach(data -> beatmapDataArrayList.add((BeatmapData) data));
        return beatmapDataArrayList;
    }

    /**
     * 获取谱面
     * 
     * @param recent 谱面参数
     * @return 谱面
     */
    public BeatmapData getBeatmap(UserRecentData recent) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        return getBeatmap(BeatmapParameters.builder().b(String.valueOf(recent.getBeatmapId())).build()).get(0);
    }

    // recent成绩

    /**
     * 获取recent成绩组
     *
     * @param parameters recent参数
     * @return 成绩组
     */
    public ArrayList<UserRecentData> getRecent(UserRecentParameters parameters) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        ArrayList<UserRecentData> beatmapDataArrayList = new ArrayList<>();
        downloader.get(parameters).forEach(data -> beatmapDataArrayList.add((UserRecentData) data));
        return beatmapDataArrayList;
    }

    /**
     * 获取recent成绩
     *
     * @param info 用户信息
     * @return recent成绩
     */
    public UserRecentData getRecent(CommandRecent.UsernameAndIndexAndMode info) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException, RecentScoreNotEnough
    {
        ArrayList<UserRecentData> recents = getRecent(UserRecentParameters.builder()
                .u(info.getUsername())
                .limit(String.valueOf(info.getIndex()))
                .type("string")
                .m(String.valueOf(info.getMode()))
                .build());

        if (recents.size() < info.getIndex()) throw new RecentScoreNotEnough(recents.size(), info.getIndex(), info.getMode());

        return recents.get(info.getIndex() - 1);
    }
    
    // score成绩

    /**
     * 获取Scores成绩组
     *
     * @param parameters scores参数
     * @return scores成绩组
     */
    public ArrayList<UserScoreData> getScore(UserScoreParameters parameters) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        ArrayList<UserScoreData> beatmapDataArrayList = new ArrayList<>();
        downloader.get(parameters).forEach(data -> beatmapDataArrayList.add((UserScoreData) data));
        beatmapDataArrayList.sort(UserScoreData::compareTo);

        return beatmapDataArrayList;
    }

    /**
     * 获取Score成绩
     *
     * @param info 用户信息
     * @param recent recent成绩
     * @return score成绩
     */
    public UserScoreData getScore(CommandRecent.UsernameAndIndexAndMode info, UserRecentData recent) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException, RelatedScoreNotFoundException {
        ArrayList<UserScoreData> dataSet = getScore(UserScoreParameters.builder()
                .b(String.valueOf(recent.getBeatmapId()))
                .u(info.getUsername())
                .limit(String.valueOf(info.getIndex()))
                .type("string")
                .m(String.valueOf(info.getMode()))
                .build());

        for (UserScoreData data : dataSet)
        {
            if (    data.count50 == recent.count50 &&
                    data.count100 == recent.count100 &&
                    data.count300 == recent.count300 &&
                    data.countgeki == recent.countgeki &&
                    data.countkatu == recent.countKatu &&
                    data.countmiss == recent.countMiss &&
                    data.enabledMods == recent.enabledMods &&
                    data.maxcombo == recent.maxCombo)
            {
                return data;
            }
        }

        throw new RelatedScoreNotFoundException();
    }

    // 计算
    public double getAcc(UserRecentData recent, BeatmapData beatmap)
    {
        return recent.getAcc(beatmap.getMode());
    }
}
