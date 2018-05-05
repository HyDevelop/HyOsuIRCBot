package cc.moecraft.irc.osubot.command.commands.fun;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.Random;

/**
 * 此类由 Hykilpikonna 在 2018/05/03 创建!
 * Created by Hykilpikonna on 2018/05/03!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class CommandRoll extends Command
{
    public CommandRoll()
    {
        super("roll");
    }

    /**
     * 随机数
     *  !roll                           随机数, 最小值为0, 最大值为100
     *  !roll [最大]                    随机数, 最小值为0
     *  !roll [最小] [最大]             随机数
     *  !roll [最小] [最大] [精准度]    随机数, 精准度0.1 的话就填10
     *
     * @param event 事件
     * @param sender 发送者的irc用户 ( 在osu的irc服务器的话用户名就是osu的用户名 )
     * @param channel 频道
     * @param command 指令名 ( 不包含指令参数 )
     * @param args 指令参数 ( 不包含指令名 )
     */
    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        int min = 0, max = 100, decimalPlace = 1;

        if (args.size() == 1) max = Integer.parseInt(args.get(0));
        if (args.size() > 1)
        {
            min = Integer.parseInt(args.get(0));
            max = Integer.parseInt(args.get(1));
        }
        if (args.size() > 2) decimalPlace = Integer.parseInt(args.get(2));

        Main.getMessenger().respond(event, String.format("Roll到的数: %s", getRandomNumber(min, max, decimalPlace)));
    }

    /**
     * 获取随机数
     *
     * @param min 最小
     * @param max 最大
     * @param decimalPlace 小数点位置 ( 0.1 的话就写 10 )
     * @return 随机数
     */
    public static double getRandomNumber(int min, int max, int decimalPlace)
    {
        return ((double) min + new Random().nextInt((min + max) * decimalPlace)) / (double) decimalPlace;
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.fun.roll";
    }
}
