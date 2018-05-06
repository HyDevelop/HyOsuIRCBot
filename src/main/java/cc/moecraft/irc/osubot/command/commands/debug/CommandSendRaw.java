package cc.moecraft.irc.osubot.command.commands.debug;

import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.utils.ArrayUtils;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class CommandSendRaw extends Command
{
    public CommandSendRaw()
    {
        super("sendraw", "sr");
    }

    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        String msg = ArrayUtils.getTheRestArgsAsString(args, 0);
        event.getBot().sendRaw().rawLine(msg);
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.debug.sendraw";
    }
}
