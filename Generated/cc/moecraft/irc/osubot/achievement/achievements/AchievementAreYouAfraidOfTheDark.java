package cc.moecraft.irc.osubot.achievement.achievements.AchievementAreYouAfraidOfTheDark;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:41 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:41!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementAreYouAfraidOfTheDark extends Achievement
{
    @Override
    public long getId() { return 125; }

    @Override
    public String getName() { return "Are You Afraid Of The Dark?"; }

    @Override
    public String getGrouping() { return "Mod Introduction"; }

    @Override
    public long getOrdering() { return 10; }

    @Override
    public String getSlug() { return "all-intro-flashlight"; }

    @Override
    public String getDescription() { return "Harder than it looks, probably because it's hard to look."; }

    @Override
    public int getMode() { return %mode%; }

    @Override
    public String getTutorial() { return "<br> Pass any map with FL mod. Can use other mods in the same play.<br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
