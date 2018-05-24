package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:04 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:04!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
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
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Pass a 2 star map without using EZ/NF/HT mods. <div id=\"achievementdescription3  "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
