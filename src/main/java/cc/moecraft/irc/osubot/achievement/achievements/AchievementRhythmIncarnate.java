package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:38:14 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:38:14!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementRhythmIncarnate extends Achievement
{
    @Override
    public long getId() { return 78; }

    @Override
    public String getName() { return "Rhythm Incarnate"; }

    @Override
    public String getGrouping() { return "Skill"; }

    @Override
    public long getOrdering() { return 4; }

    @Override
    public String getSlug() { return "taiko-skill-pass-8"; }

    @Override
    public String getDescription() { return "Feel the beat. Become the beat."; }

    @Override
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " [https://osu.ppy.sh/b/111680 xi - Ascension to Heaven] +DT [https://osu.ppy.sh/b/39825 IOSYS - Marisa wa Taihen na Kanbu de Tomatte Ikimashita] +DT [https://osu.ppy.sh/s/418826 DystopiaGround - AugoEidEs] +HR (osu!catch) [https://osu.ppy.sh/b/553820 MiddleIsland - Achromat] +DT (osu!mania)"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
