package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:55 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:55!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementFeelinIt extends Achievement
{
    @Override
    public long getId() { return 176; }

    @Override
    public String getName() { return "Feelin' It"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-feelinit"; }

    @Override
    public String getDescription() { return "Got with the times."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Your max combo must be the same as maps BPM (or half of it) Full combo [https://osu.ppy.sh/b/1267643\" >Harumachi Clover [Kisses' Easy]]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
