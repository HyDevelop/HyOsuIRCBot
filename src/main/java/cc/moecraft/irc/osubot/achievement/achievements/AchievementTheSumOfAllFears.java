package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:45 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:45!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementTheSumOfAllFears extends Achievement
{
    @Override
    public long getId() { return 148; }

    @Override
    public String getName() { return "The Sum Of All Fears"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-nuked"; }

    @Override
    public String getDescription() { return "Unfortunate."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Full combo but miss the last note.   [https://osu.ppy.sh/b/34527 Silver Forest - Marisa Spark] [Easy] [https://osu.ppy.sh/b/7960 Cold Play - Clocks] [Easy]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
