package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:13 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:13!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementFaceYourDemons extends Achievement
{
    @Override
    public long getId() { return 74; }

    @Override
    public String getName() { return "Face Your Demons"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 4; }

    @Override
    public String getSlug() { return "taiko-skill-pass-4"; }

    @Override
    public String getDescription() { return "The first trials are now behind you, but are you a match for the Oni?"; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Pass a 4 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (osu!standard) [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane]+DT (taiko)"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
