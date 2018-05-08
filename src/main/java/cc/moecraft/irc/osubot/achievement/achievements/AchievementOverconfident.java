package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mod;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementOverconfident implements Achievement
{
    @Override
    public String officialAchievementName()
    {
        return "Overconfident";
    }

    @Override
    public String tutorial()
    {
        return "任意谱面开DT+HR打出低于40%的准确率";
    }

    @Override
    public long recommendedMap()
    {
        return 434841;
    }

    @Override
    public Mods mods()
    {
        return new Mods(Mod.DoubleTime, Mod.HardRock);
    }

    @Override
    public double completionTimeInMinutes()
    {
        return 1;
    }

    @Override
    public int averageRetryCount()
    {
        return 1;
    }
}
