package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:31 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:31!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementSRanker extends Achievement
{
    @Override
    public long getId() { return 15; }

    @Override
    public String getName() { return "S-Ranker"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-rank-s"; }

    @Override
    public String getDescription() { return "Accuracy is really underrated."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Get five S ranks (or SS) in 24 hours "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
