package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:26 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:26!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementBreakthrough extends Achievement
{
    @Override
    public long getId() { return 114; }

    @Override
    public String getName() { return "Breakthrough"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 9; }

    @Override
    public String getSlug() { return "mania-skill-fc-4"; }

    @Override
    public String getDescription() { return "Many skills mastered, rolled into one."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.breakthrough"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
