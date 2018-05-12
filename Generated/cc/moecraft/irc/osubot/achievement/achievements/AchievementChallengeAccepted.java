package cc.moecraft.irc.osubot.achievement.achievements.AchievementChallengeAccepted;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:14 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:14!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementChallengeAccepted extends Achievement
{
    @Override
    public long getId() { return 39; }

    @Override
    public String getName() { return "Challenge Accepted"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-challenge_accepted"; }

    @Override
    public String getDescription() { return "Oh, you're ON."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Pass an approved map. <a href=\"https://osu.ppy.sh/p/beatmaplist?m=-1&r=6&g=0&la=0&ra=\">LIST<a>.<br> <br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
