package cc.moecraft.irc.osubot.achievement.achievements.AchievementJune2017Spotlight;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:08 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:08!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementJune2017Spotlight extends Achievement
{
    @Override
    public long getId() { return 166; }

    @Override
    public String getName() { return "June 2017 Spotlight"; }

    @Override
    public String getGrouping() { return "Beatmap Spotlights"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "spotlight-2017-06"; }

    @Override
    public String getDescription() { return "Innocence destroyed."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Clear all maps in June 2017 <a href=\"https://osu.ppy.sh/p/packlist?t=r\">Spotlight</a>.<br> Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play.<br><br> NF+DT these 3 maps in osu!mania mode to unlock achievement: <a href=\"https://osu.ppy.sh/s/529574\">1</a> <a href=\"https://osu.ppy.sh/s/569903\">2</a> <a href=\"https://osu.ppy.sh/s/575053\">3</a>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
