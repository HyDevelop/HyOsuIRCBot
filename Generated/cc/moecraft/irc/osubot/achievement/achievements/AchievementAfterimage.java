package cc.moecraft.irc.osubot.achievement.achievements.AchievementAfterimage;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:20 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:20!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementAfterimage extends Achievement
{
    @Override
    public long getId() { return 136; }

    @Override
    public String getName() { return "Afterimage"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-afterimage"; }

    @Override
    public String getDescription() { return "But a glimpse of its true self."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Pass any map with HT+HD mods. <a href=\"https://osu.ppy.sh/b/259\">TRF - Survival dAnce ~no no cry more~</a> is only 17 seconds for fast achievement."; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
