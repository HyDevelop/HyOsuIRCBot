package cc.moecraft.irc.osubot.command.commands;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.factory.DAOFactory;
import cc.moecraft.irc.osubot.model.Osu;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        Main.getMessenger().respond(event, "指令列表: http://help.bot.hydev.org");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.command.help";
    }
}
