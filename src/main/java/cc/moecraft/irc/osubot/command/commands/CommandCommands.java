package cc.moecraft.irc.osubot.command.commands;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class CommandCommands extends Command
{
    public CommandCommands()
    {
        super("commands", "cmds", "cmd", "listcmd", "指令");
    }

    @Override
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        return MultiLanguageText.directText(Main.getCommandManager().getCommandNameList().toString());
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.command.commands";
    }
}
