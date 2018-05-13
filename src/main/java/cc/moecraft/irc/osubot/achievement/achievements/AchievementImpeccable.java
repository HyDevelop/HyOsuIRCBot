package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:51 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:51!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementImpeccable extends Achievement
{
    @Override
    public long getId() { return 160; }

    @Override
    public String getName() { return "Impeccable"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-impeccable"; }

    @Override
    public String getDescription() { return "Speed matters not to the exemplary."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Pass a 4+ star map with DT+PF mods.   [https://osu.ppy.sh/b/150272 Hatsuki Yura - Yami no Kodomo tachi] [Hard] [https://osu.ppy.sh/b/382042 Hatsuki Yura - Nightmare -Overture-] [Insane]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
