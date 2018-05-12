package cc.moecraft.irc.osubot.achievement.achievements.AchievementDreamcatcher;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:59 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:59!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementDreamcatcher extends Achievement
{
    @Override
    public long getId() { return 85; }

    @Override
    public String getName() { return "Dreamcatcher"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 6; }

    @Override
    public String getSlug() { return "fruits-skill-pass-7"; }

    @Override
    public String getDescription() { return "No fruit, only dreams now."; }

    @Override
    public int getMode() { return fruits; }

    @Override
    public String getTutorial() { return "<br> <a href=\"https://osu.ppy.sh/b/104229\">Team Nekokan - Can't Defeat Airman</a><br> <a href=\"https://osu.ppy.sh/b/736215&m=0\">Panda Eyes & Teminite - Highscore</a><br> <a href=\"https://osu.ppy.sh/b/901854\">Sendan Life</a><br> <a href=\"https://osu.ppy.sh/b/95733\">rog-unlimitation [Insane]</a> +DT (taiko)<br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
