package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:14 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:14!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementKeepingTime extends Achievement
{
    @Override
    public long getId() { return 95; }

    @Override
    public String getName() { return "Keeping Time"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 5; }

    @Override
    public String getSlug() { return "taiko-skill-fc-1"; }

    @Override
    public String getDescription() { return "Don, then katsu. Don, then katsu.."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.keepingtime"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
