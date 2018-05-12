package cc.moecraft.irc.osubot.achievement.achievements.AchievementRecklessAbandon;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:22 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:22!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementRecklessAbandon extends Achievement
{
    @Override
    public long getId() { return 140; }

    @Override
    public String getName() { return "Reckless Abandon"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-reckless"; }

    @Override
    public String getDescription() { return "Throw it all to the wind."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Full combo a 3+ star map with SD+HR mods. <br><br> <a href=\"https://osu.ppy.sh/b/97399&m=0\">W.T. Orchestra - William Tell Overture[AngelHoney]</a>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
