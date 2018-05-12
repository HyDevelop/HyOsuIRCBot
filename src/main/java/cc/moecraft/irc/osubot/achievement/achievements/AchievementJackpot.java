package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:30:51 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:30:51!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementJackpot extends Achievement
{
    @Override
    public long getId() { return 41; }

    @Override
    public String getName() { return "Jackpot"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-jackpot"; }

    @Override
    public String getDescription() { return "Lucky sevens is a mild understatement."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Get 222,222 (or 6,666,666 etc.) score on any ranked map."; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
