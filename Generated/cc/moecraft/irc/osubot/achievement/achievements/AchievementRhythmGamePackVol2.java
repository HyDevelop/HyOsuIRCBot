package cc.moecraft.irc.osubot.achievement.achievements.AchievementRhythmGamePackVol2;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:05 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:05!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementRhythmGamePackVol2 extends Achievement
{
    @Override
    public long getId() { return 19; }

    @Override
    public String getName() { return "Rhythm Game Pack vol.2"; }

    @Override
    public String getGrouping() { return "Beatmap Packs"; }

    @Override
    public long getOrdering() { return 3; }

    @Override
    public String getSlug() { return "all-packs-rhythm-2"; }

    @Override
    public String getDescription() { return "You just can't stop the beat."; }

    @Override
    public int getMode() { return osu; }

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
