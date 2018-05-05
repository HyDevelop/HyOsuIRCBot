package cc.moecraft.irc.osubot.command.commands.fun;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandPing extends Command
{
    public CommandPing()
    {
        super("ping");
    }

    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        Main.getMessenger().respond(event, "Pong!");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.fun.ping";
    }
}
