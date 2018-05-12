package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:47 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:47!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
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
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Play [https://osu.ppy.sh/b/529285 LeaF - Evanescent] with HT+HD+NF mods (need to get 10,000 score minimum)."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}