package cc.moecraft.irc.osubot.command.commands.management;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class CommandReload extends Command
{
    public CommandReload()
    {
        super("reload");
    }

    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        Main.getMessenger().respond(event, "开始重载...");
        long start = System.currentTimeMillis();

        Main.getConfig().reload();
        Main.getPermissionConfig().reload();

        Main.getMessenger().respond(event, "重载完成! 耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.managing.reload";
    }
}
