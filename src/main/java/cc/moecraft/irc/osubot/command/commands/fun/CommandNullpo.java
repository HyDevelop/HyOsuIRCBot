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
public class CommandNullpo extends Command
{
    public CommandNullpo()
    {
        super("nullpo", "nurupo");
    }

    @Override
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        return MultiLanguageText.directText("Gah!");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.fun.nullpo";
    }
}
