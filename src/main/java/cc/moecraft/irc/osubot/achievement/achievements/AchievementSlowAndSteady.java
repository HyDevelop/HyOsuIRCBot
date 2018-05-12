package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:46 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:46!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementSlowAndSteady extends Achievement
{
    @Override
    public long getId() { return 151; }

    @Override
    public String getName() { return "Slow And Steady"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-slowandsteady"; }

    @Override
    public String getDescription() { return "Win the race, or start again."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Pass a 3+ star map with HT+PF mods. [https://osu.ppy.sh/b/1059390 Drop - Granat] [Insane] [https://osu.ppy.sh/b/486513 FELT - In my room] [Tranquility] [https://osu.ppy.sh/b/569636&m=0 Agressor Bunx - Tornado (Original Mix)][Insane]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
