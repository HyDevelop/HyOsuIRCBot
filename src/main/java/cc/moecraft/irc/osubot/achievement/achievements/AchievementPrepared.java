package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:30:56 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:30:56!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementPrepared extends Achievement
{
    @Override
    public long getId() { return 138; }

    @Override
    public String getName() { return "Prepared"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-prepared"; }

    @Override
    public String getDescription() { return "Do it for real next time."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Full combo any map with NF mod.<a href=\"https://osu.ppy.sh/b/34529\"> Silver Forest - Marisa Spark</a> is only 21 seconds long."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
