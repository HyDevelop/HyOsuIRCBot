package cc.moecraft.irc.osubot.achievement.achievements.AchievementJanuaryFebruary2017Spotlight;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:06 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:06!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementJanuaryFebruary2017Spotlight extends Achievement
{
    @Override
    public long getId() { return 162; }

    @Override
    public String getName() { return "January/February 2017 Spotlight"; }

    @Override
    public String getGrouping() { return "Beatmap Spotlights"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "spotlight-2017-01"; }

    @Override
    public String getDescription() { return "Two for the price of one."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Clear all maps in January/February 2017 <a href=\"https://osu.ppy.sh/p/packlist?t=r\">Spotlight</a>.<br> Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play.<br><br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
