package cc.moecraft.irc.osubot.achievement.achievements.AchievementJackOfAllTrades;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:17 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:17!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
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
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Exact solution not known. Get 5000+ playcount in all game modes (osu!standard/taiko/mania/catch the beat) <br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
