package cc.moecraft.irc.osubot.listener;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.Main;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class CommandListener extends ListenerAdapter
{
    @Override
    public void onConnect(ConnectEvent event) throws Exception
    {
        Main.getLogger().log(" ### OsuBot已连接 ###");
    }

    /**
     * 接收消息
     * @param event 事件
     */
    @Override
    public void onMessage(MessageEvent event) throws Exception
    {
        Channel channel = event.getChannel();
        User sender = event.getUser();
        String message = event.getMessage();

        if (Main.isDebug())
        {
            Main.getLogger().debug("收到频道消息: ");
            Main.getLogger().debug("- 频道: ");
            Main.getLogger().debug("  - 名字:   " + channel.getName());
            Main.getLogger().debug("  - ID:     " + channel.getChannelId());
            Main.getLogger().debug("- 用户: ");
            Main.getLogger().debug("  - 昵称:   " + (sender != null ? sender.getNick() : "没有昵称"));
            Main.getLogger().debug("  - 登录名: " + (sender != null ? sender.getLogin() : "没有Login"));
            Main.getLogger().debug("  - UUID:   " + (sender != null ? sender.getUserId() : "没有ID"));
            Main.getLogger().debug("- 消息: " + message);
        }
        else
        {
            Main.getLogger().log(String.format("[I] [%s] %s: %s", channel.getName(), sender.getNick(), message));
        }
        
        Main.getCommandManager().runCommand(event, message, sender, channel);
    }

    @Override
    public void onPrivateMessage(PrivateMessageEvent event) throws Exception
    {
        User sender = event.getUser();
        String message = event.getMessage();

        if (Main.isDebug())
        {
            Main.getLogger().debug("收到私聊消息: ");
            Main.getLogger().debug("- 用户: ");
            Main.getLogger().debug("  - 昵称:   " + (sender != null ? sender.getNick() : "没有昵称"));
            Main.getLogger().debug("  - 登录名: " + (sender != null ? sender.getLogin() : "没有Login"));
            Main.getLogger().debug("  - UUID:   " + (sender != null ? sender.getUserId() : "没有ID"));
            Main.getLogger().debug("- 消息: " + message);
        }
        else
        {
            Main.getLogger().log(String.format("[P] %s: %s", sender.getNick(), message));
        }
        
        Main.getCommandManager().runCommand(event, message, sender, null);
    }
}
