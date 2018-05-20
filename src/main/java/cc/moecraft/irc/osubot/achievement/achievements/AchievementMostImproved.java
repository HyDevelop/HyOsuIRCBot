package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:32 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:32!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementMostImproved extends Achievement
{
    @Override
    public long getId() { return 16; }

    @Override
    public String getName() { return "Most Improved"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-improved"; }

    @Override
    public String getDescription() { return "Now THAT is improvement."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.mostimproved"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
