package cc.moecraft.irc.osubot.command.commands.management.power;

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
public class CommandStop extends Command
{
    public CommandStop()
    {
        super("stop", "shutdown");
    }

    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        System.exit(0);
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.managing.stop";
    }
}
