package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.command.commands.osu.CommandRecent;
import cc.moecraft.irc.osubot.osu.data.*;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RecentScoreNotEnough;
import cc.moecraft.irc.osubot.osu.exceptions.RelatedScoreNotFoundException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.BeatmapParameters;
import cc.moecraft.irc.osubot.osu.parameters.UserBestParameters;
import cc.moecraft.irc.osubot.osu.parameters.UserRecentParameters;
import cc.moecraft.irc.osubot.osu.parameters.UserScoreParameters;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static cc.moecraft.irc.osubot.osu.Api.PP_SCALE;

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

    // best成绩

    /**
     * 获取Best成绩组
     *
     * @param parameters Best参数
     * @return Best成绩组
     */
    public ArrayList<UserBestData> getUserBest(UserBestParameters parameters) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        ArrayList<UserBestData> beatmapDataArrayList = new ArrayList<>();
        downloader.get(parameters).forEach(data -> beatmapDataArrayList.add((UserBestData) data));
        beatmapDataArrayList.sort(UserBestData::compareTo);

        return beatmapDataArrayList;
    }

    // 获取从某天开始计算的进步

    public OsuTrackData getProgressByDays(String usernameOrID, int mode, String type, int days) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        return getProgressByDays(UserBestParameters.builder()
            .u(usernameOrID)
            .type(type)
            .limit("100")
            .m(String.valueOf(mode))
            .build(), days);
    }

    /**
     * 计算玩家在多少天内的进步
     *
     * @param parameters 参数
     * @param days 天数
     * @return 进步数据
     */
    public OsuTrackData getProgressByDays(UserBestParameters parameters, int days) throws JsonEmptyException, MalformedURLException, RequiredParamIsNullException, IllegalAccessException
    {
        ArrayList<UserBestData> userBestData = getUserBest(parameters);
        ArrayList<UserBestData> validUserBestData = new ArrayList<>();
        ArrayList<PpAndValidity> ppAndValidity = new ArrayList<>(); // 因为PP有PP单独的计算规律, 所以要单独记录

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days);
        Date targetDate = cal.getTime();

        // 按日期过滤
        int nextStart = 0;

        // 放入有效的
        for (UserBestData oneData : userBestData)
        {
            nextStart++;
            try {
                if (isBestDataValid(oneData, targetDate))
                {
                    ppAndValidity.add(new PpAndValidity(oneData.getPp(), true));
                    validUserBestData.add(oneData);
                }
                else
                {
                    nextStart--;
                    break;
                }
            } catch (ParseException ignored) {}
        }

        // 放入无效的
        for (int j = nextStart; j < userBestData.size(); j++)
        {
            UserBestData oneData = userBestData.get(j);

            ppAndValidity.add(new PpAndValidity(oneData.getPp(), false));
        }

        int mode = Integer.parseInt(parameters.getM());
        double changeInTotalAcc = 0, changeInAverageAcc;
        long changeInA = 0, changeInS = 0, changeInSS = 0;
        double changeInPP = 0;
        int rankedPlayCount = validUserBestData.size();

        ppAndValidity.sort(PpAndValidity::compareTo);

        // 统计PP
        for (int i = 0; i < ppAndValidity.size(); i++)
        {
            PpAndValidity onePp = ppAndValidity.get(i);

            if (!onePp.valid) continue;
            changeInPP += onePp.pp * PP_SCALE[i];
        }

        // 统计其他
        for (UserBestData oneData : validUserBestData)
        {
            switch (oneData.getRank())
            {
                case "A":
                    changeInA++;
                    break;
                case "S": case "SH":
                    changeInS++;
                    break;
                case "SS": case "SSH":
                    changeInSS++;
                    break;
                default:
                    System.out.println("未处理: Rank = " + oneData.getRank());
                    break;
            }

            changeInTotalAcc += getAcc(mode, oneData.count300, oneData.count100, oneData.count50, oneData.countmiss, oneData.countkatu, oneData.countgeki);
        }

        changeInAverageAcc = changeInTotalAcc / (double) rankedPlayCount;

        return OsuTrackData.builder()
                .countRankA(changeInA)
                .countRankS(changeInS)
                .countRankSs(changeInSS)
                .accuracy(changeInAverageAcc)
                .ppRaw(changeInPP)
                .playcount((long) rankedPlayCount)
                .build();
    }

    public boolean isBestDataValid(UserBestData oneData, Date targetDate) throws ParseException
    {
        Date thisDate = oneData.getDateObject();

        return (targetDate.compareTo(thisDate) < 0);
    }

    @Data @AllArgsConstructor
    public class PpAndValidity implements Comparable<PpAndValidity>
    {
        double pp; boolean valid;

        @Override
        public int compareTo(PpAndValidity o)
        {
            return (int) (o.pp - pp);
        }
    }


    // 计算
    public double getAcc(UserRecentData recent, BeatmapData beatmap)
    {
        return recent.getAcc(this, beatmap.getMode());
    }

    /**
     * 计算精准度
     * @param mode 模式
     * @param count300 300
     * @param count100 100
     * @param count50 50
     * @param countMiss Miss
     * @param countKatu 喝
     * @param countgeki 激
     * @return ACC
     */
    public double getAcc(int mode, int count300, int count100, int count50, int countMiss, int countKatu, int countgeki)
    {
        double acc = 0;
        switch (mode)
        {
            case 0:
                acc = (count300 * 300.0 + count100 * 100.0 + count50 * 50.0) / ((count300 + count100 + count50 + countMiss) * 300);
                break;
            case 1:
                acc = (count300 + count100 * 0.5) / (count300 + count100 + countMiss);
                break;
            case 2:
                int base = count300 + count100 + count50;
                acc = (double) base / (base + (countMiss + countKatu));
                break;
            case 3:
                acc = (double)(count50 * 50 + count100 * 100 + countKatu * 200 + (count300 + countgeki) * 300) / ((count300 + count100 + count50 + countMiss + countKatu + countgeki) * 300);
                break;
            default:
                break;
        }

        return acc;
    }
}
