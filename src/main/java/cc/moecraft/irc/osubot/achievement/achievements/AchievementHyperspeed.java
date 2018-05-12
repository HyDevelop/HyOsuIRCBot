package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:38 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:38!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementHyperspeed extends Achievement
{
    @Override
    public long getId() { return 90; }

    @Override
    public String getName() { return "Hyperspeed"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 8; }

    @Override
    public String getSlug() { return "mania-skill-pass-4"; }

    @Override
    public String getDescription() { return "Woah."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Pass a 4 star map without using EZ/NF/HT mods. <a href=\"https://osu.ppy.sh/b/315\">FAIRY FORE - Vivid</a> [Insane] (osu!standard) <a href=\"https://osu.ppy.sh/b/315\">FAIRY FORE - Vivid</a> [Insane]+DT (taiko)"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
