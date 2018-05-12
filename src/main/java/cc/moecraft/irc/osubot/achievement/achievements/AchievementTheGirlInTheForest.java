package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:53 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:53!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementTheGirlInTheForest extends Achievement
{
    @Override
    public long getId() { return 171; }

    @Override
    public String getName() { return "The Girl in the Forest"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-girlintheforest"; }

    @Override
    public String getDescription() { return "Not even the Elite Four could stop you now."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Get 95%+ acc and 151 max combo on [https://osu.ppy.sh/s/40440 S3RL - Pika Girl] Hard or Insane difficulty. Break combo after 151. "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
