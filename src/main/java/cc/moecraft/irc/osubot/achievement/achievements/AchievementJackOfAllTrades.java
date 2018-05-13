package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:36 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:36!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementJackOfAllTrades extends Achievement
{
    @Override
    public long getId() { return 45; }

    @Override
    public String getName() { return "Jack of All Trades"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-jack"; }

    @Override
    public String getDescription() { return "Good at everything."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Exact solution not known. Get 5000+ playcount in all game modes (osu!standard/taiko/mania/catch the beat) "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
