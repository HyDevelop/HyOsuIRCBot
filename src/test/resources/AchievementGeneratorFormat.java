package %class_package%;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 %current_date_and_time% 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on %current_date_and_time%!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class Achievement%name_capitalized% extends Achievement
{
    @Override
    public long getId() { return %id%; }

    @Override
    public String getName() { return "%name%"; }

    @Override
    public String getGrouping() { return "%grouping%"; }

    @Override
    public long getOrdering() { return %ordering%; }

    @Override
    public String getSlug() { return "%slug%"; }

    @Override
    public String getDescription() { return "%description%"; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "%tutorial%"; }

    @Override
    public Long getRecommendedMap() { return %recommend%; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString("%mods%"); }

    @Override
    public String getCompletionTimeInMinutes() { return "%completion_time%"; }

    @Override
    public String getAverageRetryCount() { return "%average_retry%"; }
}