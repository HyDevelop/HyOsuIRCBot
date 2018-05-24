package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:58 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:58!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementTimeAndAHalf extends Achievement
{
    @Override
    public long getId() { return 122; }

    @Override
    public String getName() { return "Time And A Half"; }

    @Override
    public String getGrouping() { return "Mod Introduction"; }

    @Override
    public long getOrdering() { return 10; }

    @Override
    public String getSlug() { return "all-intro-doubletime"; }

    @Override
    public String getDescription() { return "Having a right ol' time. One and a half of them, almost."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return "achievement.timeandahalf"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
