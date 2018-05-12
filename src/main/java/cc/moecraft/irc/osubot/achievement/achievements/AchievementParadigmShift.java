package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:09 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:09!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementParadigmShift extends Achievement
{
    @Override
    public long getId() { return 67; }

    @Override
    public String getName() { return "Paradigm Shift"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 3; }

    @Override
    public String getSlug() { return "osu-skill-fc-5"; }

    @Override
    public String getDescription() { return "Surprisingly difficult."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Full combo a 5 star map without using EZ/NF/HT mods (don't miss slider ends)."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}