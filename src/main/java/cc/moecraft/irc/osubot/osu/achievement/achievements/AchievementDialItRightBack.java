package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:00 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:00!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementDialItRightBack extends Achievement
{
    @Override
    public long getId() { return 126; }

    @Override
    public String getName() { return "Dial It Right Back"; }

    @Override
    public String getGrouping() { return "Mod Introduction"; }

    @Override
    public long getOrdering() { return 10; }

    @Override
    public String getSlug() { return "all-intro-easy"; }

    @Override
    public String getDescription() { return "Sometimes you just want to take it easy."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return "achievement.dialitrightback"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
