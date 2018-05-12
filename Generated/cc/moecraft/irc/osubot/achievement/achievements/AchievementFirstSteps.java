package cc.moecraft.irc.osubot.achievement.achievements.AchievementFirstSteps;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:05:02 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:05:02!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementFirstSteps extends Achievement
{
    @Override
    public long getId() { return 87; }

    @Override
    public String getName() { return "First Steps"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 8; }

    @Override
    public String getSlug() { return "mania-skill-pass-1"; }

    @Override
    public String getDescription() { return "It isn't 9-to-5, but 1-to-9. Keys, that is."; }

    @Override
    public int getMode() { return mania; }

    @Override
    public String getTutorial() { return "<br> Pass a 1 star map without using EZ/NF/HT mods.<br> <div id=\"achievementdescription3\"> "; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
