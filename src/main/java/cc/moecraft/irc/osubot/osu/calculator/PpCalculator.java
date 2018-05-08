package cc.moecraft.irc.osubot.osu.calculator;

/**
 * 此类由 Hykilpikonna 在 2018/05/07 创建!
 * Created by Hykilpikonna on 2018/05/07!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class PpCalculator
{
    /**
     * 计算Mania无Mod的PP
     * @param star 星
     * @param OD OD
     * @param Score 成绩
     * @param Object 圈数
     * @param Acc 准确率
     * @return PP
     */
    public static double calculateManiaNoModPP(double star, double OD, double Score, double Object, double Acc)
    {
        double f = 64.0 - 3.0 * (OD);
        double k = Math.pow(150.0 / f * Math.pow(Acc / 100.0, 16.0), 1.8) * 2.5 * Math.min(1.15, Math.pow(Object / 1500.0, 0.3));
        double l = Math.pow(5.0 * Math.max(1.0, (star) / 0.0825) - 4.0, 3.0) / 110000.0 * (1.0 + 0.1 * Math.min(1.0, Object / 1500.0));
        double m =
                Score < 500000.0 ?
                        Score / 500000.0 * 0.1 :
                        (
                                Score < 600000.0 ?
                                        (Score - 500000.0) / 100000.0 * 0.2 + 0.1 :
                                        (Score < 700000.0 ?
                                                (Score - 600000.0) / 100000.0 * 0.35 + 0.3 :
                                                (Score < 800000.0 ?
                                                        (Score - 700000.0) / 100000.0 * 0.2 + 0.65 :
                                                        (Score < 900000.0 ?
                                                                (Score - 800000.0) / 100000.0 * 0.1 + 0.85 :
                                                                (Score - 900000.0) / 100000.0 * 0.05 + 0.95
                                                        )
                                                )
                                        )
                        );
        return Math.round(Math.pow(Math.pow(k, 1.1) + Math.pow(l * m, 1.1), 0.9090909090909091) * 1.1);
    }
}
