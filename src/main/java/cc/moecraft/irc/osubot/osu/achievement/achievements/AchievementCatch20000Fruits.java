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
public class AchievementCatch20000Fruits extends Achievement
{
    @Override
    public long getId() { return 13; }

    @Override
    public String getName() { return "Catch 20,000 fruits"; }

    @Override
    public String getGrouping() { return "Dedication"; }

    @Override
    public long getOrdering() { return 1; }

    @Override
    public String getSlug() { return "fruits-hits-20000"; }

    @Override
    public String getDescription() { return "That is a lot of dietary fiber."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.catch20000fruits"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
