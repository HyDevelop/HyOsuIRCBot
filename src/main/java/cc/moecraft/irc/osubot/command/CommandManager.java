package cc.moecraft.irc.osubot.command;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.osu.OsuUser;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandManager
{
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
        String prefix = isCommand(fullCommand, isChannel);

        if (prefix == null)
        {
            if (!isChannel && !user.getNick().equalsIgnoreCase(Main.getConfig().getUsername()) && !Main.getConfig().getStringList("BotProperties.AntiSpam.NotACommandExcludedUsernames").contains(user.getNick().toLowerCase())) // 如果是私聊并且不是自己, 回复提示
            {
                Main.getMessenger().respond(event, "NOT A COMMAND: 不是指令 ( 输入" + getPrefix() + "help显示帮助 )");
            }

            return RunResult.NOT_A_COMMAND;
        }

        // String "!ecHO hi" -> ArrayList ["!ecHO", "hi", "there"]
        ArrayList<String> args = new ArrayList<>(Arrays.asList(fullCommand.split(" ")));

        // "echo"
        String command = args.get(0).replace(prefix, "").toLowerCase();

        if (!registeredCommands.containsKey(command))
        {
            Main.getMessenger().respond(event, "UNKNOWN COMMAND: 未知指令 ( 输入" + prefix + "help显示帮助 )");
            return RunResult.COMMAND_NOT_FOUND;
        }

        // ["hi", "there"]
        args.remove(0);

        Command commandToRun = registeredCommands.get(command);

        if (!new OsuUser(user.getNick()).hasPermission(commandToRun.permissionRequired()))
        {
            Main.getMessenger().respond(event, "NO PERM: 无法执行" + prefix + command + ", 因为缺少权限");
            return RunResult.NO_PERMISSION;
        }

        registeredCommands.get(command).run(event, user, channel, command, args);

        return RunResult.SUCCESS;
    }

    public enum RunResult
    {
        NOT_A_COMMAND, COMMAND_NOT_FOUND,
        NO_PERMISSION,
        SUCCESS
    }

    /**
     * 判断一条消息是不是指令
     * @param text 消息
     * @param channel 频道
     * @return 是指令的话返回指令前缀, 不是的话返回null
     */
    public String isCommand(String text, boolean channel)
    {
        if (channel) return null;
        
        if (text.startsWith(getPrefix())) return getPrefix();

        for (String prefix : Main.getConfig().getStringList("BotProperties.EnabledCommandPrefixes"))
        {
            if (text.startsWith(prefix)) return prefix;
        }

        return null;
    }

    /**
     * 获取指令前缀
     * @return 指令前缀
     */
    public String getPrefix()
    {
        return Main.getConfig().getString("BotProperties.CommandPrefix");
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
