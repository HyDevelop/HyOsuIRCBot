package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:03 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:03!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementApproachingTheSummit extends Achievement
{
    @Override
    public long getId() { return 53; }

    @Override
    public String getName() { return "Approaching the summit"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 1; }

    @Override
    public String getSlug() { return "all-skill-highranker-4"; }

    @Override
    public String getDescription() { return "Pro tier. Welcome to the top 1,000!"; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return "achievement.approachingthesummit"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
