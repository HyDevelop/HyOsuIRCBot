package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:43 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:43!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementUnstoppable extends Achievement
{
    @Override
    public long getId() { return 145; }

    @Override
    public String getName() { return "Unstoppable"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-superhardhddt"; }

    @Override
    public String getDescription() { return "Holy shit."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.unstoppable"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
