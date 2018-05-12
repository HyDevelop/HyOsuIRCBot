package cc.moecraft.irc.osubot.achievement.achievements.AchievementEquilibrium;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:31 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:31!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementEquilibrium extends Achievement
{
    @Override
    public long getId() { return 159; }

    @Override
    public String getName() { return "Equilibrium"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-equilibrium"; }

    @Override
    public String getDescription() { return "Balance in all things."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Get an equal amount of 300's 100's 50's. Use of NF+HT is highly recommended. Minimum amount required is 15 of each type (15 300's, 15 100's, 15 50's) and misses do not count, so you can have less than 50% acc. <br><br> <a href=\"https://osu.ppy.sh/b/188110&m=0\">Chata - Papapapanda</a> [Easy] <br> <br> In the map above you need to hit 1 full slider (300), ALL circles (300), 30 sliders (100), 30 sliders (50).<br> Hitting 50's is very easy so it's recommended that you start hitting 100's on sliders first. <a href=\"https://www.youtube.com/watch?v=eDM3ggDyogI&feature=youtu.be\">VIDEO OF ACHIEVEMENT</a> <br> <br> <br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
