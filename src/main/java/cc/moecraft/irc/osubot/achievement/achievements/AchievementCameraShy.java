package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:01 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:01!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementCameraShy extends Achievement
{
    @Override
    public long getId() { return 147; }

    @Override
    public String getName() { return "Camera Shy"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-uguushy"; }

    @Override
    public String getDescription() { return "Stop being cute."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Get a play with NF+HD that WOULD have been in the top 500 leaderboard if not for the score reduction of NF. Just download any <a href=\"https://osu.ppy.sh/p/beatmaplist&s=4&r=0\">new map</a> and full combo any difficulty with NF+HD mods."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
