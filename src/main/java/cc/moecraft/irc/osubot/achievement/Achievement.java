package cc.moecraft.irc.osubot.achievement;

import cc.moecraft.irc.osubot.osu.Mods;
import lombok.NoArgsConstructor;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@NoArgsConstructor
public abstract class Achievement
{
    /**
     * 官方的成就名
     * @return 成就名
     */
    public abstract String officialAchievementName();

    /**
     * 成就攻略
     * @return 攻略
     */
    public abstract String tutorial();

    /**
     * 推荐的谱面
     * @return 谱面ID (不是组ID)
     */
    public abstract long recommendedMap();

    /**
     * 推荐的mod名称
     * @return mods
     */
    public abstract Mods mods();

    /**
     * 一次性成功耗时
     * @return 耗时 (分钟)
     */
    public abstract double completionTimeInMinutes();

    /**
     * 平均重试次数
     * @return 重试次数
     */
    public abstract int averageRetryCount();
}
