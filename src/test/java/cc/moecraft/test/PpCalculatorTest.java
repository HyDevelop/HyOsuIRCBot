package cc.moecraft.test;

import cc.moecraft.irc.osubot.osu.calculator.PpCalculator;
import cc.moecraft.logger.DebugLogger;

/**
 * 此类由 Hykilpikonna 在 2018/05/07 创建!
 * Created by Hykilpikonna on 2018/05/07!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class PpCalculatorTest
{
    private static DebugLogger logger = new DebugLogger("PpCalculatorTest", true);

    public static void main(String[] args)
    {
        logger.debug("Mania测试 (*1.9, OD 6, Score 617225, ACC 92.69, Objects 326): ");

        logger.debug("- " + PpCalculator.calculateManiaNoModPP(1.9, 6, 617225, 92.69, 326) + " PP");
    }
}
