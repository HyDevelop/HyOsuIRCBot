package cc.moecraft.irc.osubot.achievement.achievements.AchievementSognare;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:28 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:28!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementSognare extends Achievement
{
    @Override
    public long getId() { return 153; }

    @Override
    public String getName() { return "Sognare"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-sognare"; }

    @Override
    public String getDescription() { return "A dream in stop-motion, soon forever gone."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Play <a href=\"https://osu.ppy.sh/b/529285\">LeaF - Evanescent</a> with HT+HD+NF mods (need to get 10,000 score minimum)."; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
