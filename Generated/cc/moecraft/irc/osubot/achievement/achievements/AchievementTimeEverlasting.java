package cc.moecraft.irc.osubot.achievement.achievements.AchievementTimeEverlasting;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:57 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:57!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementTimeEverlasting extends Achievement
{
    @Override
    public long getId() { return 101; }

    @Override
    public String getName() { return "Time Everlasting"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 5; }

    @Override
    public String getSlug() { return "taiko-skill-fc-7"; }

    @Override
    public String getDescription() { return "Not a single beat escapes you."; }

    @Override
    public int getMode() { return taiko; }

    @Override
    public String getTutorial() { return "<br> Full combo a 7 star map without using EZ/NF/HT mods (don't miss slider ends).<br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
