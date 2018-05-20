package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:15 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:15!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementBigDrums extends Achievement
{
    @Override
    public long getId() { return 97; }

    @Override
    public String getName() { return "Big Drums"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 5; }

    @Override
    public String getSlug() { return "taiko-skill-fc-3"; }

    @Override
    public String getDescription() { return "Bigger scores to match."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.bigdrums"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
