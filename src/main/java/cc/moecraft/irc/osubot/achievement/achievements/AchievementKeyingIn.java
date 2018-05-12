package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:40 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:40!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementKeyingIn extends Achievement
{
    @Override
    public long getId() { return 112; }

    @Override
    public String getName() { return "Keying In"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 9; }

    @Override
    public String getSlug() { return "mania-skill-fc-2"; }

    @Override
    public String getDescription() { return "Finding your groove."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Full combo a 2 star map without using EZ/NF/HT mods (don't miss slider ends)."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
