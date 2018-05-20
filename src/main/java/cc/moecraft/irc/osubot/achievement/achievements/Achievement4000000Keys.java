package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:30 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:30!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class Achievement4000000Keys extends Achievement
{
    @Override
    public long getId() { return 48; }

    @Override
    public String getName() { return "4,000,000 Keys"; }

    @Override
    public String getGrouping() { return "Dedication"; }

    @Override
    public long getOrdering() { return 3; }

    @Override
    public String getSlug() { return "mania-hits-4000000"; }

    @Override
    public String getDescription() { return "Is this the end of the rainbow?"; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.4000000keys"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
