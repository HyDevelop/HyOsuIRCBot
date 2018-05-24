package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:30 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:30!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementNovember2017Spotlight extends Achievement
{
    @Override
    public long getId() { return 182; }

    @Override
    public String getName() { return "November 2017 Spotlight"; }

    @Override
    public String getGrouping() { return "Beatmap Spotlights"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "spotlight-2017-11"; }

    @Override
    public String getDescription() { return "The end of an era, and the start of something new!"; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return "achievement.november2017spotlight"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
