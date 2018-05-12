package cc.moecraft.irc.osubot.achievement.achievements.AchievementJustOneSecond;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:19 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:19!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementJustOneSecond extends Achievement
{
    @Override
    public long getId() { return 135; }

    @Override
    public String getName() { return "Just One Second"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-onesecond"; }

    @Override
    public String getDescription() { return "And suddenly.. gone."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Pass AR9 map with HD+FL mods.<br><br> <a href=\"https://osu.ppy.sh/b/10848\">ESTi feat. Various Artists - Zero Fill Love</a> [Insane+]"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
