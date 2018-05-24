package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:49 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:49!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementOurMechanicalBenefactors extends Achievement
{
    @Override
    public long getId() { return 156; }

    @Override
    public String getName() { return "Our Mechanical Benefactors"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-ourbenefactors"; }

    @Override
    public String getDescription() { return "Human, please explain directive \"GREED\"."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Pass [https://osu.ppy.sh/b/260489 Traktion - The Near Distant Future] with 90% or higher accuracy."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
