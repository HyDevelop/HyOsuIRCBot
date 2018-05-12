package cc.moecraft.irc.osubot.achievement.achievements.AchievementBuildingSteam;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:49 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:49!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementBuildingSteam extends Achievement
{
    @Override
    public long getId() { return 65; }

    @Override
    public String getName() { return "Building Steam"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 3; }

    @Override
    public String getSlug() { return "osu-skill-fc-3"; }

    @Override
    public String getDescription() { return "Hey, this isn't so bad."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Full combo a 3 star map without using EZ/NF/HT mods (don't miss slider ends).<br><br> <a href=\"https://osu.ppy.sh/b/315\">FAIRY FORE - Vivid</a> [Insane] (taiko) <br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
