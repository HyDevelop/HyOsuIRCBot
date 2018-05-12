package cc.moecraft.irc.osubot.achievement.achievements.AchievementASliceOfLife;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:57 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:57!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementASliceOfLife extends Achievement
{
    @Override
    public long getId() { return 79; }

    @Override
    public String getName() { return "A Slice Of Life"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 6; }

    @Override
    public String getSlug() { return "fruits-skill-pass-1"; }

    @Override
    public String getDescription() { return "Hey, this fruit catching business isn't bad."; }

    @Override
    public int getMode() { return fruits; }

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
