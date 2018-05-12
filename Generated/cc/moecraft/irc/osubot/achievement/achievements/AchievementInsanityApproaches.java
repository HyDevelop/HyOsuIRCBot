package cc.moecraft.irc.osubot.achievement.achievements.AchievementInsanityApproaches;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:46 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:46!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementInsanityApproaches extends Achievement
{
    @Override
    public long getId() { return 58; }

    @Override
    public String getName() { return "Insanity Approaches"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 2; }

    @Override
    public String getSlug() { return "osu-skill-pass-4"; }

    @Override
    public String getDescription() { return "You're not twitching, you're just ready."; }

    @Override
    public int getMode() { return osu; }

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
