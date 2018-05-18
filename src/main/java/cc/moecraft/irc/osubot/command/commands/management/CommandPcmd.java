package cc.moecraft.irc.osubot.command.commands.management;

import cc.moecraft.irc.osubot.command.ChannelCommand;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import cc.moecraft.irc.osubot.utils.ArrayUtils;
import com.google.common.collect.ImmutableMap;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.UserHostmask;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类由 Hykilpikonna 在 2018/05/13 创建!
 * Created by Hykilpikonna on 2018/05/13!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class CommandPcmd extends Command implements ChannelCommand
{
    public static final Pattern regexToFindUsername = Pattern.compile("(?<=\")(.*)(?=\")");
    public static final Pattern regexToRemoveUsername = Pattern.compile("(?=\")(.*)(?<=\") ");

    public CommandPcmd()
    {
        super("pcmd", "playercommand");
    }

    /**
     * 用法:
     *  !pcmd "[用户名]" [指令]        以某个用户的身份执行指令
     *
     * @param event 事件
     * @param sender 发送者的irc用户 ( 在osu的irc服务器的话用户名就是osu的用户名 )
     * @param channel 频道
     * @param command 指令名 ( 不包含指令参数 )
     * @param args 指令参数 ( 不包含指令名 )
     */
    @Override
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        return process(event, sender, channel, command, args, false);
    }

    @Override
    public MultiLanguageText channel(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        return process(event, sender, channel, command, args, true);
    }

    public MultiLanguageText process(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args, boolean isChannel)
    {
        if (args.size() < 1)
            return MultiLanguageText.directText("用法: %prefix%pcmd \"[用户名]\" [指令]");

        String arg = ArrayUtils.getTheRestArgsAsString(args, 0);

        Matcher matcher = regexToFindUsername.matcher(arg);

        if (!matcher.find())
            return MultiLanguageText.directText("用户名要加引号");

        String username = matcher.group();
        String commandToExecute = regexToRemoveUsername.matcher(arg).replaceAll("");

        PircBotX bot = event.getBot();

        ImmutableMap.Builder<String, String> tags = ImmutableMap.builder();
        UserHostmask source = bot.getConfiguration().getBotFactory().createUserHostmask(bot, username);

        try {
            if (isChannel)
            {
                bot.getInputParser().processCommand(
                        username, source, "PRIVMSG",
                        String.format(":%s!cho@ppy.sh PRIVMSG %s :~%s", username, channel.getName(), commandToExecute),
                        Arrays.asList(username, "~" + commandToExecute), tags.build());
            }
            else
            {
                bot.getInputParser().processCommand(
                        username, source, "PRIVMSG",
                        String.format(":%s!cho@ppy.sh PRIVMSG %s :~%s", username, bot.getNick(), commandToExecute),
                        Arrays.asList(username, "~" + commandToExecute), tags.build());
            }
        } catch (IOException e) {
            return MultiLanguageText.directText("文件异常: " + Arrays.toString(e.getStackTrace()));
        }

        return MultiLanguageText.empty();
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.managing.pcmd";
    }
}
