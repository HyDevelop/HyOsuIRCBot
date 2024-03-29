package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:02 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:02!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class Achievement500Combo extends Achievement
{
    @Override
    public long getId() { return 1; }

    @Override
    public String getName() { return "500 Combo"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-combo-500"; }

    @Override
    public String getDescription() { return "500 big ones! You're moving up in the world!"; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.500combo"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
