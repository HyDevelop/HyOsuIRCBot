package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:34 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:34!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementQuickDraw extends Achievement
{
    @Override
    public long getId() { return 42; }

    @Override
    public String getName() { return "Quick Draw"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-quick_draw"; }

    @Override
    public String getDescription() { return "It's high noon."; }

    @Override
    public int getMode() { return 4; }

    @Override
    public String getTutorial() { return " Be the first one to set a score on a new ranked/qualified map (no scores on ALL difficulties from other players). Works with any osu! game mode. Look for new qualified maps [https://osu.ppy.sh/p/beatmaplist?m=-1&r=11&g=0&la=0&ra= here<a>. NF+DT is recommended for fast passing so no one snipes your achievement. <b>NOTE: a map must be played in it's original game mode. Mania/taiko recommended as not that many people play those game modes.</b>   "; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
