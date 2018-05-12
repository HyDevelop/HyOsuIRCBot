package cc.moecraft.irc.osubot.achievement.achievements.AchievementToTheCore;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:20 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:20!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementToTheCore extends Achievement
{
    @Override
    public long getId() { return 137; }

    @Override
    public String getName() { return "To The Core"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-tothecore"; }

    @Override
    public String getDescription() { return "In for a penny, in for a pound. Pounding bass, that is."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Pass any NIGHTCORE map with DT/NC mod. If you don't have any nightcore maps <a href=\"https://osu.ppy.sh/b/89547&m=0\"> here's one</a>."; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
