package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:11 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:11!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementMyFirstDon extends Achievement
{
    @Override
    public long getId() { return 71; }

    @Override
    public String getName() { return "My First Don"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 4; }

    @Override
    public String getSlug() { return "taiko-skill-pass-1"; }

    @Override
    public String getDescription() { return "Marching to the beat of your own drum. Literally."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Pass a 1 star map without using EZ/NF/HT mods. <div id=\"achievementdescription3  "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
