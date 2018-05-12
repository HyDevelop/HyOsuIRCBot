package cc.moecraft.irc.osubot.achievement.achievements.AchievementRealitt;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:29 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:29!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementRealitt extends Achievement
{
    @Override
    public long getId() { return 155; }

    @Override
    public String getName() { return "Realität"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-realitat"; }

    @Override
    public String getDescription() { return "A moonlight butterfly, and beacons of three."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Get 90% or higher accuracy on <a href=\"https://osu.ppy.sh/s/227126\">LeaF - Evanescent</a> in osu!standard<br> Requires S rank in osu!mania (95%+ accuracy) with 700k+ total score.<br> 93%+ in osu!catch (could be lower?)<br><br> does not work with HT/EZ/NF"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
