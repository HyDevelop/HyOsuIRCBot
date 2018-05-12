package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementOverconfident extends Achievement
{
    @Override
    public long getId() { return 0; }

    @Override
    public String getName() { return "Overconfident"; }

    @Override
    public String getGrouping() { return null; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return null; }

    @Override
    public String getDescription() { return null; }

    @Override
    public int getMode() { return 0; }

    @Override
    public String getTutorial() { return "任意谱面开DT+HR打出低于40%的准确率"; }

    @Override
    public Long getRecommendedMap() { return 434841L; }

    @Override
    public Mods getMods() { return new Mods(DoubleTime, HardRock); }

    @Override
    public String getCompletionTimeInMinutes() { return "1"; }

    @Override
    public String getAverageRetryCount() { return "1"; }
}
