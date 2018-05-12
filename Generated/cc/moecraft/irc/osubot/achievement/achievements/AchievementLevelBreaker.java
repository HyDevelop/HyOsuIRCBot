package cc.moecraft.irc.osubot.achievement.achievements.AchievementLevelBreaker;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:05:06 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:05:06!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
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
    public int getMode() { return mania; }

    @Override
    public String getTutorial() { return "<br> Full combo a 6 star map without using EZ/NF/HT mods (don't miss slider ends).<br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
