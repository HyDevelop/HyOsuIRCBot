package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:30:52 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:30:52!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementObsessed extends Achievement
{
    @Override
    public long getId() { return 43; }

    @Override
    public String getName() { return "Obsessed"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-obsessed"; }

    @Override
    public String getDescription() { return "COMPLETION AT ALL COSTS."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Retry any ranked song 100 times and then pass/full combo it (must be done in 24 hours or less!). Make sure to track your plays using osu! website and NOT in-game playcount tracker. You need to get 10k+ score each play or it won't count. "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
