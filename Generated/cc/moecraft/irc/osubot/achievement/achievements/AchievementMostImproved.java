package cc.moecraft.irc.osubot.achievement.achievements.AchievementMostImproved;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:13 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:13!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementMostImproved extends Achievement
{
    @Override
    public long getId() { return 16; }

    @Override
    public String getName() { return "Most Improved"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-improved"; }

    @Override
    public String getDescription() { return "Now THAT is improvement."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> On a mapset you never played before get a D rank with at least 100,000 score on first try and then S on the second try. <br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
