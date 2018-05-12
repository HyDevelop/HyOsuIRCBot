package cc.moecraft.irc.osubot.achievement.achievements.Achievement3000000DrumHits;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:11 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:11!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class Achievement3000000DrumHits extends Achievement
{
    @Override
    public long getId() { return 33; }

    @Override
    public String getName() { return "3,000,000 Drum Hits"; }

    @Override
    public String getGrouping() { return "Dedication"; }

    @Override
    public long getOrdering() { return 2; }

    @Override
    public String getSlug() { return "taiko-hits-3000000"; }

    @Override
    public String getDescription() { return "Truly, the Don of dons."; }

    @Override
    public int getMode() { return taiko; }

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
