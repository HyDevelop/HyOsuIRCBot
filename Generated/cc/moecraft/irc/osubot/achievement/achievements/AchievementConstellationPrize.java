package cc.moecraft.irc.osubot.achievement.achievements.AchievementConstellationPrize;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:45 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:45!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementConstellationPrize extends Achievement
{
    @Override
    public long getId() { return 56; }

    @Override
    public String getName() { return "Constellation Prize"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 2; }

    @Override
    public String getSlug() { return "osu-skill-pass-2"; }

    @Override
    public String getDescription() { return "Definitely not a consolation prize. Now things start getting hard!"; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Pass a 2 star map without using EZ/NF/HT mods.<br> <div id=\"achievementdescription3\"> "; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
