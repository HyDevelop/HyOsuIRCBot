package cc.moecraft.irc.osubot.achievement.achievements.AchievementAboveAndBeyond;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:47 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:47!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementAboveAndBeyond extends Achievement
{
    @Override
    public long getId() { return 60; }

    @Override
    public String getName() { return "Above and Beyond"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 2; }

    @Override
    public String getSlug() { return "osu-skill-pass-6"; }

    @Override
    public String getDescription() { return "A cut above the rest."; }

    @Override
    public int getMode() { return osu; }

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
