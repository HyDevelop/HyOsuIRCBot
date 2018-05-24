package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:25 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:25!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementHyperflow extends Achievement
{
    @Override
    public long getId() { return 113; }

    @Override
    public String getName() { return "Hyperflow"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 9; }

    @Override
    public String getSlug() { return "mania-skill-fc-3"; }

    @Override
    public String getDescription() { return "You can *feel* the rhythm."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.hyperflow"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
