package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:10 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:10!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementTrueTorment extends Achievement
{
    @Override
    public long getId() { return 173; }

    @Override
    public String getName() { return "True Torment"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "osu-secret-truetorment"; }

    @Override
    public String getDescription() { return "It lasts forever."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Pass <a href=\"https://osu.ppy.sh/s/594751\">Helblinde - The Solace of Oblivion</a> in osu!standard mode"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
