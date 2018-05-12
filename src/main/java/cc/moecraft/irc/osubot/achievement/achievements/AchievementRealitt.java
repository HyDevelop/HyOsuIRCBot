package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:05 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:05!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
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
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Get 90% or higher accuracy on <a href=\"https://osu.ppy.sh/s/227126\">LeaF - Evanescent</a> in osu!standard Requires S rank in osu!mania (95%+ accuracy) with 700k+ total score. 93%+ in osu!catch (could be lower?) does not work with HT/EZ/NF"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
