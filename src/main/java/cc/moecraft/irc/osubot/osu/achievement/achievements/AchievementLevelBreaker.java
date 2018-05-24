package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:26 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:26!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementLevelBreaker extends Achievement
{
    @Override
    public long getId() { return 116; }

    @Override
    public String getName() { return "Level Breaker"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 9; }

    @Override
    public String getSlug() { return "mania-skill-fc-6"; }

    @Override
    public String getDescription() { return "Finesse beyond reason."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.levelbreaker"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
