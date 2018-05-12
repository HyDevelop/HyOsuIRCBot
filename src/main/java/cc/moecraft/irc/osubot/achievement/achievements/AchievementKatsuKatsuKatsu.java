package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:31:28 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:31:28!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementKatsuKatsuKatsu extends Achievement
{
    @Override
    public long getId() { return 72; }

    @Override
    public String getName() { return "Katsu Katsu Katsu"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 4; }

    @Override
    public String getSlug() { return "taiko-skill-pass-2"; }

    @Override
    public String getDescription() { return "Hora! Ikuzo!"; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Pass a 2 star map without using EZ/NF/HT mods. <div id=\"achievementdescription3\"> "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
