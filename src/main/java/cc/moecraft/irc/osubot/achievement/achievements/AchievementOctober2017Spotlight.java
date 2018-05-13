package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:29 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:29!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementOctober2017Spotlight extends Achievement
{
    @Override
    public long getId() { return 181; }

    @Override
    public String getName() { return "October 2017 Spotlight"; }

    @Override
    public String getGrouping() { return "Beatmap Spotlights"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "spotlight-2017-10"; }

    @Override
    public String getDescription() { return "First, one must distract a punk rock girl. Then, they must put on the radio."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Clear all maps in October 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 3 maps in osu!catch mode to unlock achievement: [https://osu.ppy.sh/s/522132 1] [https://osu.ppy.sh/s/609679 2] [https://osu.ppy.sh/s/665562 3]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
