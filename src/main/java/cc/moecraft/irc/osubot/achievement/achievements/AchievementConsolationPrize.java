package cc.moecraft.irc.osubot.achievement.achievements;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.Mods;

/**
 * 此类由 Hykilpikonna 在 2018/05/132 17:37:33 用 AchievementClassGenerator 生成!
 * Created by Hykilpikonna on 2018/05/132 17:37:33!
 *
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
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
    public int getMode() { return 3; }

    @Override
    public String getTutorial() { return " Exact solution not known. Pass any difficulty of any ranked map with a D rank and more than 100,000 score without using NF/HT/SO mods (EZ mod is allowed).  Recommended low HP drain maps: [https://osu.ppy.sh/s/21323 BoA - soundscape<a> [Hard] [https://osu.ppy.sh/b/1147&m=0 IOSYS - Marisa wa Taihen na Mono wo Nusunde Ikimashita<a> [Hard]  Some screenshots: [http://haitai.jp/img/prize1.jpg http://haitai.jp/img/prize1.jpg] [http://haitai.jp/img/prize2.jpg http://haitai.jp/img/prize2.jpg] [http://haitai.jp/img/prize3.jpg http://haitai.jp/img/prize3.jpg] [http://haitai.jp/img/prize4.jpg http://haitai.jp/img/prize4.jpg] [http://haitai.jp/img/prize5.jpg http://haitai.jp/img/prize5.jpg]"; }

    @Override
    public Long getRecommendedMap() { return null; }

    @Override
    public Mods getMods() { return Mods.parseFromShortString(""); }

    @Override
    public String getCompletionTimeInMinutes() { return "未知"; }

    @Override
    public String getAverageRetryCount() { return "未知"; }
}
