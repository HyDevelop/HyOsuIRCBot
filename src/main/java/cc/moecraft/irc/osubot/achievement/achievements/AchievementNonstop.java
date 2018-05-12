package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:30:52 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:30:52!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementNonstop extends Achievement
{
    @Override
    public long getId() { return 44; }

    @Override
    public String getName() { return "Nonstop"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-nonstop"; }

    @Override
    public String getDescription() { return "Breaks? What are those?"; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Full combo 10+ minutes of any map (drain time, not including rest time). Can break combo after 10 mins. Easiest way to do this is to full combo <a href=\"https://osu.ppy.sh/s/2916\">Hyadain - Megaman Mix</a> on osu!catch (CTB) mode with EZ mod. "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
