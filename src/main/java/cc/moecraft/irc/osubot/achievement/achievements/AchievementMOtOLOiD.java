package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:25 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:25!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementMOtOLOiD extends Achievement
{
    @Override
    public long getId() { return 179; }

    @Override
    public String getName() { return "MOtOLOiD"; }

    @Override
    public String getGrouping() { return "Beatmap Packs"; }

    @Override
    public long getOrdering() { return 4; }

    @Override
    public String getSlug() { return "all-packs-motoloid"; }

    @Override
    public String getDescription() { return "Legends made manifest, by the mappers of the Guild."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return "achievement.motoloid"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
