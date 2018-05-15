package cc.moecraft.irc.osubot.command.commands.management.power;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;

/**
 * 此类由 Hykilpikonna 在 2018/05/02 创建!
 * Created by Hykilpikonna on 2018/05/02!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class CommandStop extends Command
{
    private static HashMap<String, Long> confirmMap = new HashMap<>(); // 玩家名对应时间

    public CommandStop()
    {
        super("stop", "shutdown");
    }

    /**
     * 用法:
     *  !stop           结束进程
     *
     * @param event 事件
     * @param sender 发送者的irc用户 ( 在osu的irc服务器的话用户名就是osu的用户名 )
     * @param channel 频道
     * @param command 指令名 ( 不包含指令参数 )
     * @param args 指令参数 ( 不包含指令名 )
     */
    @Override
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        AtomicBoolean shouldReturn = new AtomicBoolean(false);

        for (Map.Entry<String, Long> entry : new HashMap<>(confirmMap).entrySet())
        {
            String key = entry.getKey();
            long value = entry.getValue();

            if ((System.currentTimeMillis() - value) >= 5000)
            {
                confirmMap.remove(key);
                if (key.equals(sender.getNick()))
                {
                    shouldReturn.set(true);
                    return MultiLanguageText.directText("请求超过5秒, 请重新输入");
                }
            }
        }

        if (shouldReturn.get()) return MultiLanguageText.empty();

        boolean confirmed = confirmMap.containsKey(sender.getNick());

        if (confirmed)
        {
            Main.getMessenger().respondIRC(event, MultiLanguageText.directText("已执行关闭命令, 关闭成功不会发送通知"));
            System.exit(0);
            return MultiLanguageText.empty();
        }
        else
        {
            Main.getMessenger().respondIRC(event, MultiLanguageText.directText("此指令将会关闭程序进程, 关闭进程后无法通过指令重新启动, 想要关闭监听器输入%prefix%disable, %prefix%enable"));
            Main.getMessenger().respondIRC(event, MultiLanguageText.directText("想继续关闭的话在5秒内再输入一遍%prefix%stop"));

            confirmMap.put(sender.getNick(), System.currentTimeMillis());

            return MultiLanguageText.empty();
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.managing.stop";
    }
}
