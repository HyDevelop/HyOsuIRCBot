package cc.moecraft.irc.osubot.achievement.achievements.AchievementMeticulous;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:30 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:30!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementMeticulous extends Achievement
{
    @Override
    public long getId() { return 157; }

    @Override
    public String getName() { return "Meticulous"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-meticulous"; }

    @Override
    public String getDescription() { return "The circle goes here, and then here, and then here.."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Pass a 3+ star map with EZ+PF mods.<br><br> <a href=\"https://osu.ppy.sh/b/932223&m=0\">Mike Greene - Bill Nye the Science Guy Theme Song (Chinese Intro)</a><br> <a href=\"https://osu.ppy.sh/b/452185\">Disasterpeace - Jolly Frolic</a><br> <a href=\"https://osu.ppy.sh/s/2086\">Sonic X Intro</a><br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
