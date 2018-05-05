package cc.moecraft.irc.osubot.command.commands.management.power;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/02 创建!
 * Created by Hykilpikonna on 2018/05/02!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class CommandDisable extends Command
{
    public CommandDisable()
    {
        super("disable");
    }

    /**
     * 用法:
     *  !disable        停用机器人
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
        Main.setEnableListening(false);
        Main.getMessenger().respond(event, "监听已禁用!");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.managing.disable";
    }
}
