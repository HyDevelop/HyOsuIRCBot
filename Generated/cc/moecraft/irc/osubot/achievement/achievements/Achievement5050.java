package cc.moecraft.irc.osubot.achievement.achievements.Achievement5050;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:33 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:33!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class Achievement5050 extends Achievement
{
    @Override
    public long getId() { return 168; }

    @Override
    public String getName() { return "50/50"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-5050"; }

    @Override
    public String getDescription() { return "Half full or half empty, that's a whole lot of fifty."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Get 50 50's on any map.<br><br> Get 100% accuracy <a href=\"https://osu.ppy.sh/b/34529&m=2\">Silver Forest - Marisa Spark [Normal]</a> in osu!catch mode.<br> <a href=\"https://osu.ppy.sh/b/21184\">Rhapsody - Emerald Sword [Light]</a>. Start but don't complete sliders to get guaranteed 50 points.<br> <br> NF/HT mods do not work."; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
