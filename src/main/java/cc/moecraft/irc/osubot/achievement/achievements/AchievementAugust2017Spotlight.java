package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:28 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:28!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementAugust2017Spotlight extends Achievement
{
    @Override
    public long getId() { return 169; }

    @Override
    public String getName() { return "August 2017 Spotlight"; }

    @Override
    public String getGrouping() { return "Beatmap Spotlights"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "spotlight-2017-08"; }

    @Override
    public String getDescription() { return "Ah, yes. Something just like this."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Clear all maps in August 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 3 maps in osu!mania mode to unlock achievement: [https://osu.ppy.sh/s/300685 1] [https://osu.ppy.sh/s/453386 2] [https://osu.ppy.sh/s/613792 3]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
