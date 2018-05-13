package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:54 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:54!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementTheFirmamentMoves extends Achievement
{
    @Override
    public long getId() { return 174; }

    @Override
    public String getName() { return "The Firmament Moves"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-celestialmovement"; }

    @Override
    public String getDescription() { return "Number fourteen? More like number one."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Pass Normal difficulty on [https://osu.ppy.sh/b/1145134 cYsmix - Moonlight Sonata] with HDDTHR mods"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
