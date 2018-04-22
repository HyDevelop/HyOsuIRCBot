package cc.moecraft.irc.osubot.command.commands;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.model.Osu;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class CommandHelp extends Command
{

    private Logger logger = LoggerFactory.getLogger(CommandHelp.class);

    private Osu osuDao = new Osu();

    public CommandHelp()
    {
        super("help", new ArrayList<>(Arrays.asList("?", "帮助")));
    }

    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        List<Osu> all = osuDao.findAll();
        logger.info(all.toString());
        Main.getMessenger().respond(event, "还没有帮助!");
    }
}
