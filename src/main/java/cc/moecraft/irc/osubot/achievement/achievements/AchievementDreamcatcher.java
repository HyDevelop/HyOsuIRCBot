package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:19 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:19!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementDreamcatcher extends Achievement
{
    @Override
    public long getId() { return 85; }

    @Override
    public String getName() { return "Dreamcatcher"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 6; }

    @Override
    public String getSlug() { return "fruits-skill-pass-7"; }

    @Override
    public String getDescription() { return "No fruit, only dreams now."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " [https://osu.ppy.sh/b/104229 Team Nekokan - Can't Defeat Airman] [https://osu.ppy.sh/b/736215&m=0 Panda Eyes & Teminite - Highscore] [https://osu.ppy.sh/b/901854 Sendan Life] [https://osu.ppy.sh/b/95733 rog-unlimitation [Insane]] +DT (taiko)"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
