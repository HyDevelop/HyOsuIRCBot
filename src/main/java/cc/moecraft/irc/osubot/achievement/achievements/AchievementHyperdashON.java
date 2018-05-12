package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:33 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:33!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
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
