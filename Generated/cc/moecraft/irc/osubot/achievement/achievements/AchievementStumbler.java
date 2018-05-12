package cc.moecraft.irc.osubot.achievement.achievements.AchievementStumbler;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:15 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:15!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementStumbler extends Achievement
{
    @Override
    public long getId() { return 40; }

    @Override
    public String getName() { return "Stumbler"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-stumbler"; }

    @Override
    public String getDescription() { return "No regrets."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Full combo any map with B rank or lower. <br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
