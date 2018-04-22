package cc.moecraft.irc.osubot.command.commands;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.model.Osu;
import cc.moecraft.irc.osubot.service.OsuService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandHelp extends Command
{
    @JbootrpcService
    private OsuService osuService;

    public CommandHelp()
    {
        super("help", new ArrayList<>(Arrays.asList("?", "帮助")));
    }

    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        //List<Osu> all = osuService.getAll();
        //System.out.println(all.toString());
        Main.getMessenger().respond(event, "还没有帮助!");
    }
}
