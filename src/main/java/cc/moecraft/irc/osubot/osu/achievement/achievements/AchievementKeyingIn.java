package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:25 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:25!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementKeyingIn extends Achievement
{
    @Override
    public long getId() { return 112; }

    @Override
    public String getName() { return "Keying In"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 9; }

    @Override
    public String getSlug() { return "mania-skill-fc-2"; }

    @Override
    public String getDescription() { return "Finding your groove."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.keyingin"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
