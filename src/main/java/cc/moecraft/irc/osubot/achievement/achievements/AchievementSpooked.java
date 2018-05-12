package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:56 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:56!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementSpooked extends Achievement
{
    @Override
    public long getId() { return 178; }

    @Override
    public String getName() { return "Spooked"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-spooked"; }

    @Override
    public String getDescription() { return "Something moved. It wasn't your cursor!"; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Pass any map with FL mod and get a D rank. [https://osu.ppy.sh/b/857602&m=0 toby fox - Quiet Water [Still]] [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid [Insane]]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
