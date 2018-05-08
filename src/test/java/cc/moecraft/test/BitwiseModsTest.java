package cc.moecraft.test;

import cc.moecraft.irc.osubot.osu.Mod;
import cc.moecraft.irc.osubot.osu.Mods;
import cc.moecraft.logger.DebugLogger;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class BitwiseModsTest
{
    private static DebugLogger logger = new DebugLogger("BitwiseModsTest", true);

    public static void main(String[] args)
    {
        Mods mods = new Mods().add(Mod.HardRock, Mod.DoubleTime, Mod.Hidden);

        printMods(mods);

        mods.remove(Mod.HardRock);
        logger.debug("移除HR");

        printMods(mods);
    }

    public static void printMods(Mods mods)
    {
        logger.debug("Mods: " + mods);
    }
}
