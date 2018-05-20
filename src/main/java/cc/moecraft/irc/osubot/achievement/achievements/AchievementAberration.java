package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:11 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:11!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementAberration extends Achievement
{
    @Override
    public long getId() { return 70; }

    @Override
    public String getName() { return "Aberration"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 3; }

    @Override
    public String getSlug() { return "osu-skill-fc-8"; }

    @Override
    public String getDescription() { return "They said it couldn't be done. They were wrong."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.aberration"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
