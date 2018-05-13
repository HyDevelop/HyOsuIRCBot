package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:37 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:37!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementTimeDilation extends Achievement
{
    @Override
    public long getId() { return 134; }

    @Override
    public String getName() { return "Time Dilation"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-tidi"; }

    @Override
    public String getDescription() { return "Longer is shorter when all is said and done."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Pass a map that is 10 minutes or longer with DT mod (check map time AFTER you put DT on). [https://osu.ppy.sh/b/9007 Yoko Ishida - paraparaMAX I] easy but long [https://osu.ppy.sh/s/8338 Lucky Star no Minna - Kumikyoku 'Lucky Star Douga'] medium [https://osu.ppy.sh/b/247037 Lucky Star no Minna - Kumikyoku 'Lucky Star Douga'] hard but short"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
