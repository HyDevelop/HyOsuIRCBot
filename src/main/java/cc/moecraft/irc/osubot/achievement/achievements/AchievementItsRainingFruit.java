package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:18 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:18!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementItsRainingFruit extends Achievement
{
    @Override
    public long getId() { return 83; }

    @Override
    public String getName() { return "It's Raining Fruit"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 6; }

    @Override
    public String getSlug() { return "fruits-skill-pass-5"; }

    @Override
    public String getDescription() { return "And you can catch them all."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Pass a 5 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/95733 07th Expansion - rog-unlimitation [Insane]] (taiko)"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
