package cc.moecraft.irc.osubot.achievement.achievements.AchievementIsThisRealLife;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:25 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:25!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementIsThisRealLife extends Achievement
{
    @Override
    public long getId() { return 146; }

    @Override
    public String getName() { return "Is This Real Life?"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-supersuperhardhddt"; }

    @Override
    public String getDescription() { return "You did NOT just pull that off."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Full combo a map with AR11 OD11 HP11 and over 260BPM. <br> <br> <a href=\"https://osu.ppy.sh/b/20737\">ERIKA - Destination Nowhere</a> [Hard] with HDDTHR unlocks this achievement. "; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
