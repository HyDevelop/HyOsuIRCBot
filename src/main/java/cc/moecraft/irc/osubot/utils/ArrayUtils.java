package cc.moecraft.irc.osubot.utils;

import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuUser;
import org.pircbotx.User;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/26 创建!
 * Created by Hykilpikonna on 2018/04/26!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class ArrayUtils
{
    /**
     * 获取一个指定index后面的所有args
     *
     * 例子:
     *   处理前:
     *   - args = ["say", "hello", "world"]
     *   - index = 1
     *   处理后:
     *     "hello world"
     *
     * @param args args
     * @param index 位置
     */
    public static String getTheRestArgsAsString(ArrayList<String> args, int index)
    {
        StringBuilder result = new StringBuilder();

        for (int i = index; i < args.size() - 1; i++)
        {
            result.append(args.get(i)).append(" ");
        }

        return result.append(args.get(args.size() - 1)).toString();
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
