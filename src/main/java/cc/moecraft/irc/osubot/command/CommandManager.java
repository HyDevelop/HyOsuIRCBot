package cc.moecraft.irc.osubot.command;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.management.OsuUser;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.NOTATIONDatatypeValidator;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.Arrays;
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
     * @return 执行结果
     */
    public RunResult runCommand(GenericMessageEvent event, String fullCommand, User user, Channel channel)
    {
        if (!isCommand(fullCommand))
        {
            if (channel == null) // 如果是私聊, 回复提示
            {
                Main.getMessenger().respond(event, "NOT A COMMAND: 不是指令 ( 输入" + getPrefix() + "help显示帮助 )");
            }

            return RunResult.NOT_A_COMMAND;
        }

        // String "!ecHO hi" -> ArrayList ["!ecHO", "hi", "there"]
        ArrayList<String> args = new ArrayList<>(Arrays.asList(fullCommand.split(" ")));

        // "echo"
        String command = args.get(0).replace(getPrefix(), "").toLowerCase();

        if (!registeredCommands.containsKey(command))
        {
            Main.getMessenger().respond(event, "UNKNOWN COMMAND: 未知指令 ( 输入" + getPrefix() + "help显示帮助 )");
            return RunResult.COMMAND_NOT_FOUND;
        }

        // ["hi", "there"]
        args.remove(0);

        Command commandToRun = registeredCommands.get(command);

        if (new OsuUser(user.getNick()).hasPermission(commandToRun.permissionRequired()))
        {
            Main.getMessenger().respond(event, "NO PERMISSION: 您没有权限使用" + getPrefix() + command);
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
     * @return 是不是指令
     */
    public boolean isCommand(String text)
    {
        return text.startsWith(Main.getConfig().getString("BotProperties.CommandPrefix"));
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
