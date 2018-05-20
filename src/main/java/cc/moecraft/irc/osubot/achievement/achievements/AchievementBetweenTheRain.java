package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:20 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:20!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementBetweenTheRain extends Achievement
{
    @Override
    public long getId() { return 106; }

    @Override
    public String getName() { return "Between The Rain"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 7; }

    @Override
    public String getSlug() { return "fruits-skill-fc-4"; }

    @Override
    public String getDescription() { return "No umbrella needed."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.betweentherain"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
