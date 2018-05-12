package cc.moecraft.irc.osubot.achievement.achievements.AchievementHyperdashON;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:58 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:58!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementHyperdashON extends Achievement
{
    @Override
    public long getId() { return 82; }

    @Override
    public String getName() { return "Hyperdash ON!"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 6; }

    @Override
    public String getSlug() { return "fruits-skill-pass-4"; }

    @Override
    public String getDescription() { return "Time and distance is no obstacle to you."; }

    @Override
    public int getMode() { return fruits; }

    @Override
    public String getTutorial() { return "<br> Pass a 4 star map without using EZ/NF/HT mods.<br><br> <a href=\"https://osu.ppy.sh/b/315\">FAIRY FORE - Vivid</a> [Insane] (osu!standard)<br> <a href=\"https://osu.ppy.sh/b/315\">FAIRY FORE - Vivid</a> [Insane]+DT (taiko)<br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
