package cc.moecraft.irc.osubot.achievement.achievements.AchievementKeystruck;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:05:05 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:05:05!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementKeystruck extends Achievement
{
    @Override
    public long getId() { return 111; }

    @Override
    public String getName() { return "Keystruck"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 9; }

    @Override
    public String getSlug() { return "mania-skill-fc-1"; }

    @Override
    public String getDescription() { return "The beginning of a new story."; }

    @Override
    public int getMode() { return mania; }

    @Override
    public String getTutorial() { return "<br> Full combo a 1 star map without using EZ/NF/HT mods (don't miss slider ends).<br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
