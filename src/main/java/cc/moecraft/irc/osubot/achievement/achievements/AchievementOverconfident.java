package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:56 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:56!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
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
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Pass a map with very low accuracy using DT or HR mod. Worked with 33.33% on [https://osu.ppy.sh/b/417503 Lena Park - Inori~You Raise Me Up (TV Size)] [Lanturn's Beginner]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
