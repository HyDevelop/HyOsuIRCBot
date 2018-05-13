package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:50 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:50!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
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
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Full combo a map with CS7.8 or higher. Use HR mod to increase Circle Size. You can also use HT mod for easier achievement. [https://osu.ppy.sh/b/20382 Bill Nye the Science Guy][Hard] +HR [https://osu.ppy.sh/b/860486&m=0 toby fox - Quiet Water] [TheOnlyLeon's Flowing] +HR"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
