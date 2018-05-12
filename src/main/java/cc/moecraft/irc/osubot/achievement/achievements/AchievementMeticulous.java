package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:06 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:06!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
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
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Pass a 3+ star map with EZ+PF mods. <a href=\"https://osu.ppy.sh/b/932223&m=0\">Mike Greene - Bill Nye the Science Guy Theme Song (Chinese Intro)</a> <a href=\"https://osu.ppy.sh/b/452185\">Disasterpeace - Jolly Frolic</a> <a href=\"https://osu.ppy.sh/s/2086\">Sonic X Intro</a>"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
