package cc.moecraft.irc.osubot.osu.achievement.achievements;

import cc.moecraft.irc.osubot.osu.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:24 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:24!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementInternetPackVol4 extends Achievement
{
    @Override
    public long getId() { return 36; }

    @Override
    public String getName() { return "Internet! Pack vol.4"; }

    @Override
    public String getGrouping() { return "Beatmap Packs"; }

    @Override
    public long getOrdering() { return 2; }

    @Override
    public String getSlug() { return "all-packs-internet-4"; }

    @Override
    public String getDescription() { return "Must.. have.. more.. memes.."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "achievement.internetpackvol4"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
