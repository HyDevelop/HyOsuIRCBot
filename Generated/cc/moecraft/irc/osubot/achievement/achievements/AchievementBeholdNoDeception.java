package cc.moecraft.irc.osubot.achievement.achievements.AchievementBeholdNoDeception;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:23 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:23!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementBeholdNoDeception extends Achievement
{
    @Override
    public long getId() { return 142; }

    @Override
    public String getName() { return "Behold No Deception"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-deception"; }

    @Override
    public String getDescription() { return "That wasn't easy at all!"; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Full combo a 4+ star map with EZ mod.<br><br> <a href=\"https://osu.ppy.sh/s/141\">FAIRY FORE - Vivid</a>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
