package cc.moecraft.irc.osubot.command;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.exceptions.CommandNotFoundException;
import cc.moecraft.irc.osubot.command.exceptions.NotACommandException;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import cc.moecraft.irc.osubot.osu.OsuUser;
import lombok.Getter;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class CommandManager
{
    @Getter
    private Map<String, Command> registeredCommands;    // 已注册的指令, String 是指令名, Command 是指令对象

    /**
     * 用于管理/执行指令
     */
    public CommandManager()
    {
        registeredCommands = new HashMap<>();
    }

    /**
     * 注册指令
     * @param command 指令
     * @return 是否注册成功
     */
    public boolean registerCommand(Command command)
    {
        if (registeredCommands.containsKey(command.getName()))
        {
            return false;
        }
        else
        {
            registeredCommands.put(command.getName().toLowerCase(), command);
            command.getAlias().forEach(alias -> registeredCommands.put(alias.toLowerCase(), command));
            return true;
        }
    }

    /**
     * 自动找到注册过的指令对象运行
     *
     * 例子:
     *  !ecHO hi there
     *
     * @param event 事件
     * @param fullCommand 完整指令
     * @param user 用户名
     * @param channel 频道
     * @param isChannel 是不是从频道发出的
     * @return 执行结果
     */
    public RunResult runCommand(GenericMessageEvent event, String fullCommand, User user, Channel channel, boolean isChannel)
    {
        return runCommand(event, fullCommand, user, channel, isChannel, false);
    }

    /**
     * 自动找到注册过的指令对象运行
     *
     * 例子:
     *  !ecHO hi there
     *
     * @param event 事件
     * @param fullCommand 完整指令
     * @param user 用户名
     * @param channel 频道
     * @param isChannel 是不是从频道发出的
     * @param forceChannel 强制向频道回复
     * @return 执行结果
     */
    public RunResult runCommand(GenericMessageEvent event, String fullCommand, User user, Channel channel, boolean isChannel, boolean forceChannel)
    {
        try
        {
            CommandArgs commandArgs = CommandArgs.parse(fullCommand, isChannel);

            if (!Main.isEnableListening() && !commandArgs.getCommandName().equalsIgnoreCase("enable")) return RunResult.LISTENING_DISABLED;

            if (!new OsuUser(user.getNick()).hasPermission(commandArgs.getCommandRunner().permissionRequired()))
            {
                if (reply(isChannel)) Main.getMessenger().respondIRC(event, MultiLanguageText.languageNode("manager.no_permission").putVariable("command", commandArgs.getCommandName()));
                return RunResult.NO_PERMISSION;
            }

            if (isChannel) // 频道
            {
                if (commandArgs.getCommandRunner() instanceof ChannelCommand) // implement了频道指令方法的类
                    Main.getMessenger().respondIRC(event, ((ChannelCommand) commandArgs.getCommandRunner()).channel(event, user, channel, commandArgs.getCommandName(), commandArgs.getArgs()));

                if (Main.getIrcConfig().getBoolean("BotProperties.DisableChannelReply") && !forceChannel) // 关闭了频道直接回复
                    return RunResult.CHANNEL_DISABLED;
            }

            Main.getMessenger().respondIRC(event, commandArgs.getCommandRunner().run(event, user, channel, commandArgs.getCommandName(), commandArgs.getArgs()));

            return RunResult.SUCCESS;
        }
        catch (NotACommandException e)
        {
            // 频道里
            if (isChannel) return RunResult.NOT_A_COMMAND;

            // 配置里配置了忽略的用户名单
            if (Main.getIrcConfig().getStringList("BotProperties.AntiSpam.NotACommandExcludedUsernames").contains(user.getNick())) return RunResult.NOT_A_COMMAND;

            // 如果启用了监听
            if (Main.isEnableListening()) Main.getMessenger().respondIRC(event, MultiLanguageText.languageNode("manager.not_command"));

            return RunResult.NOT_A_COMMAND;
        }
        catch (CommandNotFoundException e)
        {
            if (Main.isEnableListening())
            {
                if (!reply(isChannel)) return RunResult.COMMAND_NOT_FOUND;

                // 配置里配置了忽略的用户名单
                if (Main.getIrcConfig().getStringList("BotProperties.AntiSpam.NotACommandExcludedUsernames").contains(user.getNick())) return RunResult.COMMAND_NOT_FOUND;

                Main.getMessenger().respondIRC(event, MultiLanguageText.languageNode("manager.unknown_command"));
            }

            return RunResult.COMMAND_NOT_FOUND;
        }
    }

    public boolean reply(boolean isChannel)
    {
        return !(isChannel && Main.getIrcConfig().getBoolean("BotProperties.DisableChannelReply"));
    }

    public enum RunResult
    {
        NOT_A_COMMAND, COMMAND_NOT_FOUND,
        LISTENING_DISABLED, CHANNEL_DISABLED,
        NO_PERMISSION,
        SUCCESS
    }

    /**
     * 获取指令前缀
     * @return 指令前缀
     */
    public static String getPrefix()
    {
        return Main.getIrcConfig().getString("BotProperties.CommandPrefix");
    }

    /**
     * 获取指令列表
     * @return 指令列表
     */
    public ArrayList<Command> getCommandList()
    {
        ArrayList<Command> result = new ArrayList<>();

        registeredCommands.forEach((k, v) ->
        {
            if (!result.contains(v)) result.add(v);
        });

        return result;
    }

    /**
     * 获取指令名列表
     * @return 指令名列表
     */
    public ArrayList<String> getCommandNameList()
    {
        ArrayList<String> result = new ArrayList<>();

        registeredCommands.forEach((k, v) ->
        {
            if (!result.contains(v.getName())) result.add(v.getName());
        });

        return result;
    }
}
