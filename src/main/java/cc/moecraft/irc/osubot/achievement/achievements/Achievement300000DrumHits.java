package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:30 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:30!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class Achievement300000DrumHits extends Achievement
{
    @Override
    public long getId() { return 32; }

    @Override
    public String getName() { return "300,000 Drum Hits"; }

    @Override
    public String getGrouping() { return "Dedication"; }

    @Override
    public long getOrdering() { return 2; }

    @Override
    public String getSlug() { return "taiko-hits-300000"; }

    @Override
    public String getDescription() { return "The rhythm never stops."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.300000drumhits"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
