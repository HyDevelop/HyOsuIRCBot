package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:00 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:00!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementUnstoppable extends Achievement
{
    @Override
    public long getId() { return 145; }

    @Override
    public String getName() { return "Unstoppable"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-superhardhddt"; }

    @Override
    public String getDescription() { return "Holy shit."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Pass a map with AR11 OD11 HP11 and over 260BPM.   <a href=\"https://osu.ppy.sh/b/20737\">ERIKA - Destination Nowhere</a> [Hard] with HDDTHR unlocks this achievement."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
