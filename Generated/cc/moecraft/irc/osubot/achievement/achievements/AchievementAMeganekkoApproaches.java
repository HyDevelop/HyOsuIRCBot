package cc.moecraft.irc.osubot.achievement.achievements.AchievementAMeganekkoApproaches;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:18 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:18!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementAMeganekkoApproaches extends Achievement
{
    @Override
    public long getId() { return 54; }

    @Override
    public String getName() { return "A meganekko approaches"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "mania-secret-meganekko"; }

    @Override
    public String getDescription() { return "Congratulations, you met Maria!"; }

    @Override
    public int getMode() { return mania; }

    @Override
    public String getTutorial() { return "<br> Meet Maria, the osu!mania mascot. Finish an osu!mania map with at least a 100 combo.<br> Download <a href=\"https://osu.ppy.sh/b/741477\">this map</a> and play it with EZHT mods on ultra beginner difficulty. First 2 sliders should give you over 100 combo."; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
