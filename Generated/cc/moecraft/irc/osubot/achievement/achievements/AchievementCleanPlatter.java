package cc.moecraft.irc.osubot.achievement.achievements.AchievementCleanPlatter;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:05:00 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:05:00!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementCleanPlatter extends Achievement
{
    @Override
    public long getId() { return 105; }

    @Override
    public String getName() { return "Clean Platter"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 7; }

    @Override
    public String getSlug() { return "fruits-skill-fc-3"; }

    @Override
    public String getDescription() { return "Clean only of failure. It is completely full, otherwise."; }

    @Override
    public int getMode() { return fruits; }

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
