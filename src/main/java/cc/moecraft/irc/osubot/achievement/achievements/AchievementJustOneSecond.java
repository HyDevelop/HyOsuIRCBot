package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:30:55 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:30:55!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
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
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Pass AR9 map with HD+FL mods. <a href=\"https://osu.ppy.sh/b/10848\">ESTi feat. Various Artists - Zero Fill Love</a> [Insane+]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
