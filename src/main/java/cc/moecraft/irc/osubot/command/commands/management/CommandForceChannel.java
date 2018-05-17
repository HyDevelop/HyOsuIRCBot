package cc.moecraft.irc.osubot.command.commands.management;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.ChannelCommand;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import cc.moecraft.irc.osubot.utils.ArrayUtils;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/16 创建!
 * Created by Hykilpikonna on 2018/05/16!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class CommandForceChannel extends Command implements ChannelCommand
{
    public CommandForceChannel()
    {
        super("forcechannel", "forch");
    }

    /**
     * 用法:
     *  !forch [指令]         强制启用频道内回复执行一条指令
     *
     * @param event 事件
     * @param sender 发送者的irc用户 ( 在osu的irc服务器的话用户名就是osu的用户名 )
     * @param channel 频道
     * @param command 指令名 ( 不包含指令参数 )
     * @param args 指令参数 ( 不包含指令名 )
     * @return
     */
    @Override
    public MultiLanguageText channel(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        return process(event, sender, channel, command, args, true);
    }

    @Override
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        return process(event, sender, channel, command, args, false);
    }

    public MultiLanguageText process(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args, boolean isChannel)
    {
        Main.getCommandManager().runCommand(event, ArrayUtils.getTheRestArgsAsString(args, 0), sender, channel, isChannel, true);
        return MultiLanguageText.empty();
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.managing.forch";
    }
}
