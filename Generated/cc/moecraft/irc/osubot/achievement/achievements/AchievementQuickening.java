package cc.moecraft.irc.osubot.achievement.achievements.AchievementQuickening;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:05:01 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:05:01!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementQuickening extends Achievement
{
    @Override
    public long getId() { return 108; }

    @Override
    public String getName() { return "Quickening"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 7; }

    @Override
    public String getSlug() { return "fruits-skill-fc-6"; }

    @Override
    public String getDescription() { return "A dash above normal limits."; }

    @Override
    public int getMode() { return fruits; }

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
