package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:22 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:22!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementSupremacy extends Achievement
{
    @Override
    public long getId() { return 61; }

    @Override
    public String getName() { return "Supremacy"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 2; }

    @Override
    public String getSlug() { return "osu-skill-pass-7"; }

    @Override
    public String getDescription() { return "All marvel before your prowess."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " <a href=\"https://osu.ppy.sh/b/104229\">Team Nekokan - Can't Defeat Airman</a> <a href=\"https://osu.ppy.sh/b/736215&m=0\">Panda Eyes & Teminite - Highscore</a> <a href=\"https://osu.ppy.sh/b/901854\">Sendan Life</a> <a href=\"https://osu.ppy.sh/b/95733\">rog-unlimitation [Insane]</a> +DT (taiko)"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
