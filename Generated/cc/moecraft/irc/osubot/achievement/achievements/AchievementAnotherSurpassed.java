package cc.moecraft.irc.osubot.achievement.achievements.AchievementAnotherSurpassed;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:05:04 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:05:04!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementAnotherSurpassed extends Achievement
{
    @Override
    public long getId() { return 92; }

    @Override
    public String getName() { return "Another Surpassed"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 8; }

    @Override
    public String getSlug() { return "mania-skill-pass-6"; }

    @Override
    public String getDescription() { return "Is there no limit to your skills?"; }

    @Override
    public int getMode() { return mania; }

    @Override
    public String getTutorial() { return "<br> Pass a 6 star map without using EZ/NF/HT mods.<br><br> <a href=\"https://osu.ppy.sh/b/315\">FAIRY FORE - Vivid [Insane]</a> +DT <br> <a href=\"https://osu.ppy.sh/b/19990\">Silver Forest - Tsurupettan</a> +DT <br> <a href=\"https://osu.ppy.sh/b/296\">Wizards In Winter [Impossible]</a> +DT (taiko)<br> "; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
