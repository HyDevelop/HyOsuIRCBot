package cc.moecraft.irc.osubot.achievement.achievements.AchievementElite;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:32 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:32!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementElite extends Achievement
{
    @Override
    public long getId() { return 161; }

    @Override
    public String getName() { return "Elite"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-elite"; }

    @Override
    public String getDescription() { return "Dangerous beat agents."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Get 1337 combo (break combo after getting it). Not all maps work.<br> <a href=\"https://osu.ppy.sh/b/651744\">supercell - Kimi no Shiranai Monogatari</a> unlocks this achievement.<br><br> Miss the first reverse slider (3 combo lost in total), get 1337 combo and miss note 6 as shown in <a href=\"https://www.youtube.com/watch?v=ciATKv5Qfbs\">this video</a><br> You should have 1211 combo on your first map break. If you don't - you missed a slider end."; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
