package cc.moecraft.irc.osubot.achievement.achievements.AchievementEclipse;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:21 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:21!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementEclipse extends Achievement
{
    @Override
    public long getId() { return 139; }

    @Override
    public String getName() { return "Eclipse"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-eclipse"; }

    @Override
    public String getDescription() { return "Something new born from absence."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Pass any map with FL+HD mods. <a href=\"https://osu.ppy.sh/b/34529\">Silver Forest - Marisa Spark</a> is a short map."; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
