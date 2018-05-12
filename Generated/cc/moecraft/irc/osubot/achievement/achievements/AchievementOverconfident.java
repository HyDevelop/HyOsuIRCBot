package cc.moecraft.irc.osubot.achievement.achievements.AchievementOverconfident;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:37 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:37!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementOverconfident extends Achievement
{
    @Override
    public long getId() { return 177; }

    @Override
    public String getName() { return "Overconfident"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-overconfident"; }

    @Override
    public String getDescription() { return "Try again later, maybe?"; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Pass a map with very low accuracy using DT or HR mod.<br> Worked with 33.33% on <a href=\"https://osu.ppy.sh/b/417503\">Lena Park - Inori~You Raise Me Up (TV Size)</a> [Lanturn's Beginner]<br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
