package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:08 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:08!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementBusinessAsUsual extends Achievement
{
    @Override
    public long getId() { return 64; }

    @Override
    public String getName() { return "Business As Usual"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 3; }

    @Override
    public String getSlug() { return "osu-skill-fc-2"; }

    @Override
    public String getDescription() { return "Two to go, please."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.businessasusual"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
