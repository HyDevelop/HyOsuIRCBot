package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:26 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:26!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementApril2017Spotlight extends Achievement
{
    @Override
    public long getId() { return 164; }

    @Override
    public String getName() { return "April 2017 Spotlight"; }

    @Override
    public String getGrouping() { return "Beatmap Spotlights"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "spotlight-2017-04"; }

    @Override
    public String getDescription() { return "Pitch.. WHAT?"; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return "achievement.april2017spotlight"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
