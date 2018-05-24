package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:24 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:24!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementVideoGamePackVol2 extends Achievement
{
    @Override
    public long getId() { return 11; }

    @Override
    public String getName() { return "Video Game Pack vol.2"; }

    @Override
    public String getGrouping() { return "Beatmap Packs"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-packs-gamer-2"; }

    @Override
    public String getDescription() { return "The sequel was no match for your skills, obviously."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.videogamepackvol2"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
