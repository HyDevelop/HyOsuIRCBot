package cc.moecraft.irc.osubot.command.commands.fun;

import cc.moecraft.irc.osubot.command.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandNullpo extends Command
{
    public CommandNullpo()
    {
        super("nullpo", "nurupo");
    }

    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        event.respond("Gah!");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.command.nullpo";
    }
}
