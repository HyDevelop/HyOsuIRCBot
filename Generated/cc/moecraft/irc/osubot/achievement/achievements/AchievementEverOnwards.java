package cc.moecraft.irc.osubot.achievement.achievements.AchievementEverOnwards;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:05:04 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:05:04!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementEverOnwards extends Achievement
{
    @Override
    public long getId() { return 91; }

    @Override
    public String getName() { return "Ever Onwards"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 8; }

    @Override
    public String getSlug() { return "mania-skill-pass-5"; }

    @Override
    public String getDescription() { return "Another challenge is just around the corner."; }

    @Override
    public int getMode() { return mania; }

    @Override
    public String getTutorial() { return "<br> Pass a 5 star map without using EZ/NF/HT mods.<br><br> <a href=\"https://osu.ppy.sh/b/95733\">07th Expansion - rog-unlimitation [Insane]</a> (taiko)<br><br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
