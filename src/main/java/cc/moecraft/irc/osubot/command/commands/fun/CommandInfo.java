package cc.moecraft.irc.osubot.command.commands.fun;

import cc.moecraft.irc.osubot.command.ChannelCommand;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
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
public class CommandInfo extends Command implements ChannelCommand
{
    public CommandInfo()
    {
        super("info", "nfo");
    }

    @Override
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        if (args.size() < 1) return MultiLanguageText.empty();

        switch (args.get(0))
        {
            case "变色":
                return MultiLanguageText.languageNode("commands.fun.info_color_change");
        }

        return MultiLanguageText.empty();
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.fun.info";
    }

    @Override
    public MultiLanguageText channel(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        return run(event, sender, channel, command, args);
    }
}
