package cc.moecraft.irc.osubot.command.commands.management;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class CommandChannel extends Command
{
    public CommandChannel()
    {
        super("channel", "chan");
    }

    /**
     * 用法:
     *  管理频道:
     *
     *  !chan join [频道名]      加入频道
     *  !chan leave [频道名]     离开频道
     *  !chan list               列出已加入的频道
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
        if (args.size() < 1 || args.size() > 2)
        {
            Main.getMessenger().respond(event, "用法: !chan [join/leave/list] <频道名>");
            return;
        }

        switch (args.get(0).toLowerCase())
        {
            case "join":
            {
                event.getBot().send().joinChannel(args.get(1));
                Main.getMessenger().respond(event, "已加入频道: " + args.get(1));
                break;
            }
            case "leave":
            {
                List<Channel> channels = new ArrayList<>(Main.getOsuBot().getUserChannelDao().getAllChannels());

                Main.getMessenger().respond(event, "正在寻找频道离开... 如果成功会提示, 失败不会提示: ");
                channels.forEach(oneChannel ->
                {
                    if (oneChannel.getName().equalsIgnoreCase(args.get(1)))
                    {
                        oneChannel.send().part();
                        Main.getMessenger().respond(event, "已离开频道: " + args.get(1));
                    }
                });
                break;
            }
            case "list": // 有个缓存bug
            {
                List<Channel> channels = new ArrayList<>(Main.getOsuBot().getUserChannelDao().getAllChannels());

                List<String> channelNames = new ArrayList<>();
                channels.forEach(oneChannel ->
                {
                    channelNames.add(oneChannel.getName());
                });

                Main.getMessenger().respond(event, "已加入的频道: " + channelNames.toString());
                break;
            }
            default:
                Main.getMessenger().respond(event, "用法: !chan [join/leave/list] <频道名>");
                break;
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.managing.channel";
    }
}
