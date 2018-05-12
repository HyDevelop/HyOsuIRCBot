package cc.moecraft.irc.osubot.achievement.achievements.AchievementCatch2000000Fruits;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:11 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:11!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementCatch2000000Fruits extends Achievement
{
    @Override
    public long getId() { return 24; }

    @Override
    public String getName() { return "Catch 2,000,000 fruits"; }

    @Override
    public String getGrouping() { return "Dedication"; }

    @Override
    public long getOrdering() { return 1; }

    @Override
    public String getSlug() { return "fruits-hits-2000000"; }

    @Override
    public String getDescription() { return "Downright healthy."; }

    @Override
    public int getMode() { return fruits; }

    @Override
    public String getTutorial() { return "无教程信息"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
