package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:15 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:15!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementBigDrums extends Achievement
{
    @Override
    public long getId() { return 97; }

    @Override
    public String getName() { return "Big Drums"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 5; }

    @Override
    public String getSlug() { return "taiko-skill-fc-3"; }

    @Override
    public String getDescription() { return "Bigger scores to match."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Full combo a 3 star map without using EZ/NF/HT mods (don't miss slider ends). [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (taiko) "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
