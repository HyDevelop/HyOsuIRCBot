package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:57 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:57!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementPerfectionist extends Achievement
{
    @Override
    public long getId() { return 120; }

    @Override
    public String getName() { return "Perfectionist"; }

    @Override
    public String getGrouping() { return "Mod Introduction"; }

    @Override
    public long getOrdering() { return 10; }

    @Override
    public String getSlug() { return "all-intro-perfect"; }

    @Override
    public String getDescription() { return "Accept nothing but the best."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Pass any map with PF mod. Can't use any other mods."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
