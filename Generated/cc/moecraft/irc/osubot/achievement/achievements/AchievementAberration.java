package cc.moecraft.irc.osubot.achievement.achievements.AchievementAberration;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:52 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:52!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementAberration extends Achievement
{
    @Override
    public long getId() { return 70; }

    @Override
    public String getName() { return "Aberration"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 3; }

    @Override
    public String getSlug() { return "osu-skill-fc-8"; }

    @Override
    public String getDescription() { return "They said it couldn't be done. They were wrong."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Full combo an 8 star map without using EZ/NF/HT mods (don't miss slider ends).<br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
