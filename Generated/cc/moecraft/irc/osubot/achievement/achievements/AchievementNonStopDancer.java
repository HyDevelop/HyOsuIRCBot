package cc.moecraft.irc.osubot.achievement.achievements.AchievementNonStopDancer;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:13 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:13!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementNonStopDancer extends Achievement
{
    @Override
    public long getId() { return 17; }

    @Override
    public String getName() { return "Non-stop Dancer"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-dancer"; }

    @Override
    public String getDescription() { return "Can you still feel your feet after that?"; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Pass <a href=\"https://osu.ppy.sh/b/9007&m=0\">Yoko Ishida - paraparaMAX I<a>. You don't need to full combo, NF mod does not work.<br> <br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
