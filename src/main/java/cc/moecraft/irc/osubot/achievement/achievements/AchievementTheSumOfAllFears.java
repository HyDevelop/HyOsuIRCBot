package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:45 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:45!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementTheSumOfAllFears extends Achievement
{
    @Override
    public long getId() { return 148; }

    @Override
    public String getName() { return "The Sum Of All Fears"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-nuked"; }

    @Override
    public String getDescription() { return "Unfortunate."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return "achievement.thesumofallfears"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
