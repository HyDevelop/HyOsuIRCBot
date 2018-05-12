package cc.moecraft.irc.osubot.achievement.achievements.AchievementInfinitesimal;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:31 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:31!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementInfinitesimal extends Achievement
{
    @Override
    public long getId() { return 158; }

    @Override
    public String getName() { return "Infinitesimal"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-infinitesimal"; }

    @Override
    public String getDescription() { return "Big word for something so very, very small."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Full combo a map with CS7.8 or higher. Use HR mod to increase Circle Size.<br> You can also use HT mod for easier achievement.<br><br> <a href=\"https://osu.ppy.sh/b/20382\">Bill Nye the Science Guy</a>[Hard] +HR<br> <a href=\"https://osu.ppy.sh/b/860486&m=0\">toby fox - Quiet Water</a> [TheOnlyLeon's Flowing] +HR"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
