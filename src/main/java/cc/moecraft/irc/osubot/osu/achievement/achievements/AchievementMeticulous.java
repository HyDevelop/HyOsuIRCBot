package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:49 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:49!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementMeticulous extends Achievement
{
    @Override
    public long getId() { return 157; }

    @Override
    public String getName() { return "Meticulous"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-meticulous"; }

    @Override
    public String getDescription() { return "The circle goes here, and then here, and then here.."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.meticulous"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
