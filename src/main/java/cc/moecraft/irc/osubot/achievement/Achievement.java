package cc.moecraft.irc.osubot.achievement;

import cc.moecraft.irc.osubot.osu.Mods;
import lombok.NoArgsConstructor;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@NoArgsConstructor
public abstract class Achievement
{
    /**
     * 官方的ID
     * @return ID
     */
    public abstract long getId();

    /**
     * 官方的成就名
     * @return 成就名
     */
    public abstract String getName();

    /**
     * 官方的Grouping, 理解为类型, 比如 "Skill"
     * @return Grouping
     */
    public abstract String getGrouping();

    /**
     * 官方的Ordering, 意义不明, 比如 8
     * @return Ordering
     */
    public abstract long getOrdering();

    /**
     * 官方的连续名字, 比如 "mania-skill-pass-4"
     * @return 连续名字
     */
    public abstract String getSlug();

    /**
     * 官方介绍, 比如 "Is there no limit to your skills?"
     * @return 介绍
     */
    public abstract String getDescription();

    /**
     * 模式, 1 - 4
     * @return 模式
     */
    public abstract int getMode();

    /**
     * 成就攻略
     * @return 攻略
     */
    public abstract String getTutorial();

    /**
     * 推荐的谱面
     * @return 谱面ID (不是组ID)
     */
    public abstract long getRecommendedMap();

    /**
     * 推荐的mod名称
     * @return getMods
     */
    public abstract Mods getMods();

    /**
     * 一次性成功耗时
     * @return 耗时 (分钟)
     */
    public abstract double getCompletionTimeInMinutes();

    /**
     * 平均重试次数
     * @return 重试次数
     */
    public abstract int getAverageRetryCount();
}
