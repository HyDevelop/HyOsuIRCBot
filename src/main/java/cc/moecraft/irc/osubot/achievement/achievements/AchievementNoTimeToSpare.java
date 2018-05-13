package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:47 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:47!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementNoTimeToSpare extends Achievement
{
    @Override
    public long getId() { return 152; }

    @Override
    public String getName() { return "No Time To Spare"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-ntts"; }

    @Override
    public String getDescription() { return "Places to be, things to do."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Full combo any map with DT mod that is 30 seconds or less (any difficulty). [https://osu.ppy.sh/b/34527 Silver Forest - Marisa Spark] is only 16 seconds long."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
