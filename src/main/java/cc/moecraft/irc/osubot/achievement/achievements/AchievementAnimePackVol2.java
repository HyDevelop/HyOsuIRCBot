package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:30:41 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:30:41!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementAnimePackVol2 extends Achievement
{
    @Override
    public long getId() { return 12; }

    @Override
    public String getName() { return "Anime Pack vol.2"; }

    @Override
    public String getGrouping() { return "Beatmap Packs"; }

    @Override
    public long getOrdering() { return 1; }

    @Override
    public String getSlug() { return "all-packs-anime-2"; }

    @Override
    public String getDescription() { return "Truly dedicated to 2D."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return "无教程信息"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
