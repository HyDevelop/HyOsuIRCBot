package cc.moecraft.irc.osubot.achievement.achievements.AchievementHourBeforeTheDawn;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:27 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:27!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementHourBeforeTheDawn extends Achievement
{
    @Override
    public long getId() { return 150; }

    @Override
    public String getName() { return "Hour Before The Dawn"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-hourbeforethedawn"; }

    @Override
    public String getDescription() { return "Eleven skies of everlasting sunrise."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Full combo any difficulty on <a href=\"https://osu.ppy.sh/b/373781\">ginkiha - EOS</a></noscript>."; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
