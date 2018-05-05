package cc.moecraft.irc.osubot.utils;

import cc.moecraft.irc.osubot.osu.OsuAPIUtils;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class ArgsUtils
{
    /**
     * 获取模式
     *
     * @param args 参数
     * @param index 位置
     * @return 如果有, 返回模式, 没有, 返回-1
     */
    public static int getMode(ArrayList<String> args, int index)
    {
        return OsuAPIUtils.getModeWithName(args.get(index));
    }
}
