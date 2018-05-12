package cc.moecraft.irc.osubot.achievement.achievements.AchievementConsolationPrize;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;
import static cc.moecraft.irc.osubot.osu.Mod.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:04:14 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:04:14!
 *
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementConsolationPrize extends Achievement
{
    @Override
    public long getId() { return 38; }

    @Override
    public String getName() { return "Consolation Prize"; }

    @Override
    public String getGrouping() { return "Hush-Hush"; }

    @Override
    public long getOrdering() { return 0; }

    @Override
    public String getSlug() { return "all-secret-consolation_prize"; }

    @Override
    public String getDescription() { return "Well, it could be worse."; }

    @Override
    public int getMode() { return osu; }

    @Override
    public String getTutorial() { return "<br> Exact solution not known. Pass any difficulty of any ranked map with a D rank and more than 100,000 score without using NF/HT/SO mods (EZ mod is allowed).<br> <br><br> Recommended low HP drain maps:<br> <a href=\"https://osu.ppy.sh/s/21323\">BoA - soundscape<a> [Hard]<br> <a href=\"https://osu.ppy.sh/b/1147&m=0\">IOSYS - Marisa wa Taihen na Mono wo Nusunde Ikimashita<a> [Hard] <br> <br>Some screenshots:<br> <a href=\"http://haitai.jp/img/prize1.jpg\">http://haitai.jp/img/prize1.jpg</a><br> <a href=\"http://haitai.jp/img/prize2.jpg\">http://haitai.jp/img/prize2.jpg</a><br> <a href=\"http://haitai.jp/img/prize3.jpg\">http://haitai.jp/img/prize3.jpg</a><br> <a href=\"http://haitai.jp/img/prize4.jpg\">http://haitai.jp/img/prize4.jpg</a><br> <a href=\"http://haitai.jp/img/prize5.jpg\">http://haitai.jp/img/prize5.jpg</a><br>"; }

    @Override
    public long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
