package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:27 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:27!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementBehindTheVeil extends Achievement
{
    @Override
    public long getId() { return 118; }

    @Override
    public String getName() { return "Behind The Veil"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 9; }

    @Override
    public String getSlug() { return "mania-skill-fc-8"; }

    @Override
    public String getDescription() { return "Supernatural!"; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Full combo an 8 star map without using EZ/NF/HT mods (don't miss slider ends)."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
