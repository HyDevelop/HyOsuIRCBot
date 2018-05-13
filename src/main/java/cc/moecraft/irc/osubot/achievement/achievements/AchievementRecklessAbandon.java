package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:40 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:40!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementRecklessAbandon extends Achievement
{
    @Override
    public long getId() { return 140; }

    @Override
    public String getName() { return "Reckless Abandon"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-reckless"; }

    @Override
    public String getDescription() { return "Throw it all to the wind."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Full combo a 3+ star map with SD+HR mods.  [https://osu.ppy.sh/b/97399&m=0 W.T. Orchestra - William Tell Overture[AngelHoney]]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
