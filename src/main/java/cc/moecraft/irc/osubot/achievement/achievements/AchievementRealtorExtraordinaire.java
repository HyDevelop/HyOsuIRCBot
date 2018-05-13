package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:48 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:48!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementRealtorExtraordinaire extends Achievement
{
    @Override
    public long getId() { return 154; }

    @Override
    public String getName() { return "Realtor Extraordinaire"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-realtor"; }

    @Override
    public String getDescription() { return "An acre-wide stride."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Full combo [https://osu.ppy.sh/b/792693 cYsmix - House With Legs] with HDDTHR mods (any difficulty)."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
