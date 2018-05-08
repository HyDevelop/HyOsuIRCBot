package cc.moecraft.irc.osubot.osu;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public interface Achievement
{
    /**
     * 官方的成就名
     * @return 成就名
     */
    String officialAchievementName();

    /**
     * 成就攻略
     * @return 攻略
     */
    String tutorial();

    /**
     * 推荐的谱面
     * @return 谱面ID (不是组ID)
     */
    long recommandedMap();

    /**
     * 推荐的mod名称
     * @return mods //TODO: 这里改成Bitwise
     */
    String mods();

    /**
     * 一次性成功耗时
     * @return 耗时 (分钟)
     */
    double completionTimeInMinutes();

    /**
     * 平均重试次数
     * @return 重试次数
     */
    int averageRetryCount();
}
