package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:27 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:27!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementMay2017Spotlight extends Achievement
{
    @Override
    public long getId() { return 165; }

    @Override
    public String getName() { return "May 2017 Spotlight"; }

    @Override
    public String getGrouping() { return "Beatmap Spotlights"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "spotlight-2017-05"; }

    @Override
    public String getDescription() { return "May your accuracy forever be swift and true."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return "achievement.may2017spotlight"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
