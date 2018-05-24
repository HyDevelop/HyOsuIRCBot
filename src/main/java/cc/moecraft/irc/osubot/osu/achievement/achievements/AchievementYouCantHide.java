package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:53 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:53!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementYouCantHide extends Achievement
{
    @Override
    public long getId() { return 172; }

    @Override
    public String getName() { return "You Can't Hide"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-youcanthide"; }

    @Override
    public String getDescription() { return "I will find you, and I will click you. All of you."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return "achievement.youcanthide"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
