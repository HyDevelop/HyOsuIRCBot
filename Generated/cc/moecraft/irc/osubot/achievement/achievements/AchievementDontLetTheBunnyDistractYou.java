package cc.moecraft.irc.osubot.achievement.achievements.AchievementDontLetTheBunnyDistractYou;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:12 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:12!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementDontLetTheBunnyDistractYou extends Achievement
{
    @Override
    public long getId() { return 6; }

    @Override
    public String getName() { return "Don't let the bunny distract you!"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-bunny"; }

    @Override
    public String getDescription() { return "The order was indeed, not a rabbit."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Get 371 combo on Normal difficulty or 447 combo on Hard difficulty <a href =\"https://osu.ppy.sh/b/8707\">Chatmonchy - Make Up! Make Up!</a><br> EZ mod is allowed and may help a lot.<br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
