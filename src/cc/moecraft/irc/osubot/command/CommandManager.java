package cc.moecraft.irc.osubot.command;

import cc.moecraft.irc.osubot.Main;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.NOTATIONDatatypeValidator;

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
            registeredCommands.put(command.getName(), command);
            command.getAlias().forEach(alias -> registeredCommands.put(alias, command));
            return true;
        }
    }

    /**
     * 自动找到注册过的指令对象运行
     *
     * 例子:
     *  !echo hi there
     *
     * @param fullCommand 完整指令
     * @param username 用户名
     * @return 执行结果
     */
    public RunResult runCommand(String fullCommand, String username)
    {
        if (!isCommand(fullCommand)) return RunResult.NOT_A_COMMAND;

        // String "!echo hi" -> ArrayList ["!echo", "hi", "there"]
        ArrayList<String> args = new ArrayList<>(Arrays.asList(fullCommand.split(" ")));

        // "echo"
        String command = args.get(0).replace(Main.getConfig().getString("BotProperties.CommandPrefix"), "");

        if (!registeredCommands.containsKey(command)) return RunResult.COMMAND_NOT_FOUND;

        // ["hi", "there"]
        args.remove(0);

        registeredCommands.get(command).run(username, command, args);  // TODO: 判断用户有没有权限

        return RunResult.SUCCESS;
    }

    public enum RunResult
    {
        NOT_A_COMMAND, COMMAND_NOT_FOUND, SUCCESS
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
}
