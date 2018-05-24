package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:03 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:03!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class Achievement750Combo extends Achievement
{
    @Override
    public long getId() { return 3; }

    @Override
    public String getName() { return "750 Combo"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-combo-750"; }

    @Override
    public String getDescription() { return "750 notes back to back? Woah."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.750combo"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
