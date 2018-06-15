package cc.moecraft.irc.osubot.utils;

import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuUser;
import cc.moecraft.utils.ArrayUtils;
import org.pircbotx.User;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class OsuArgsUtils
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

    /**
     * 获取玩家和模式
     * @param sender 发送者
     * @param args 指令
     * @return 玩家和模式
     */
    public static OsuUser.UsernameAndMode getUsernameAndModeWithArgs(User sender, ArrayList<String> args)
    {
        OsuUser.UsernameAndMode result = new OsuUser.UsernameAndMode(0, "");

        if (args.size() == 0)
        {
            result.setUsername(sender.getNick());
            result.setSelf(true);
        }
        else
        {
            int modeTemp = OsuAPIUtils.getModeWithName(args.get(0));

            if (modeTemp == -1)
            {
                result.setUsername(ArrayUtils.getTheRestArgsAsString(args, 0));
            }
            else
            {
                result.setMode(modeTemp);
                if (args.size() == 1)
                {
                    result.setUsername(sender.getNick());
                    result.setSelf(true);
                }
                else
                {
                    result.setUsername(ArrayUtils.getTheRestArgsAsString(args, 1));
                }
            }
        }

        return result;
    }
}
