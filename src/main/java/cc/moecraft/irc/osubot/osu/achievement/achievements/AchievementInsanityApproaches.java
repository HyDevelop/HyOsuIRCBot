package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:05 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:05!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementInsanityApproaches extends Achievement
{
    @Override
    public long getId() { return 58; }

    @Override
    public String getName() { return "Insanity Approaches"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 2; }

    @Override
    public String getSlug() { return "osu-skill-pass-4"; }

    @Override
    public String getDescription() { return "You're not twitching, you're just ready."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.insanityapproaches"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
