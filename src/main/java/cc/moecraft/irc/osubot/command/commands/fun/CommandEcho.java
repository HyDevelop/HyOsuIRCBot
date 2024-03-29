package cc.moecraft.irc.osubot.command.commands.fun;

import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandEcho extends Command
{
    public CommandEcho()
    {
        super("echo", "me", "say");
    }

    @Override
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        StringBuilder text = new StringBuilder().append("*");

        args.forEach(arg -> text.append(arg).append(" "));

        return MultiLanguageText.directText(text.toString());
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.fun.echo";
    }
}
