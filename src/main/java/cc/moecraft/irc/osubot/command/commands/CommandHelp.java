package cc.moecraft.irc.osubot.command.commands;

import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandHelp extends Command
{

    private Logger logger = LoggerFactory.getLogger(CommandHelp.class);


    public CommandHelp()
    {
        super("help", "?", "帮助");
    }

    @Override
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        return MultiLanguageText.languageNode("help_text");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.command.help";
    }
}
