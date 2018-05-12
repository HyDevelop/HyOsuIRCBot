package cc.moecraft.irc.osubot.achievement.achievements.AchievementScalingUp;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:44 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:44!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementScalingUp extends Achievement
{
    @Override
    public long getId() { return 52; }

    @Override
    public String getName() { return "Scaling up"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 1; }

    @Override
    public String getSlug() { return "all-skill-highranker-3"; }

    @Override
    public String getDescription() { return "Welcome to the top 5,000. Never give up!"; }

    @Override
    public int getMode() { return %mode%; }

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
