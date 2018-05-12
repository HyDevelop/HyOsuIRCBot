package cc.moecraft.irc.osubot.achievement.achievements.AchievementThrillOfTheChase;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:33 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:33!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementThrillOfTheChase extends Achievement
{
    @Override
    public long getId() { return 170; }

    @Override
    public String getName() { return "Thrill of the Chase"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-thrillofthechase"; }

    @Override
    public String getDescription() { return "My heart's beating, my hands are shaking, and I'm STILL clicking."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Full combo <a href=\"https://osu.ppy.sh/b/1043382\">cYsmix - Classic Pursuit [Advanced]<a> with DT mod"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
