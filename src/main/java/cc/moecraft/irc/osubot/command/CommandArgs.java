package cc.moecraft.irc.osubot.command;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.exceptions.CommandNotFoundException;
import cc.moecraft.irc.osubot.command.exceptions.NotACommandException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data @AllArgsConstructor
public class CommandArgs
{
    private String prefix;
    private String commandName;
    private Command commandRunner;
    private ArrayList<String> args;

    /**
     * 从字符串消息转换为CommandArgs
     * @param fullCommand 完整字符串消息
     * @param isChannel 是不是从频道发出来的
     * @return CommandArgs指令
     */
    public static CommandArgs parse(String fullCommand, boolean isChannel) throws NotACommandException, CommandNotFoundException
    {
        String prefix = getPrefix(fullCommand);

        if (prefix.equals("") && isChannel) throw new NotACommandException(); // 不是指令

        ArrayList<String> args = new ArrayList<>(Arrays.asList(fullCommand.split(" "))); // String "!ecHO hi" -> ArrayList ["!ecHO", "hi", "there"]
        String command = args.get(0).replace(prefix, "").toLowerCase(); // "echo"
        args.remove(0); // ["hi", "there"]

        if (!Main.getCommandManager().getRegisteredCommands().containsKey(command)) throw new CommandNotFoundException(); // 无法找到指令

        Command commandToRun = Main.getCommandManager().getRegisteredCommands().get(command);

        return new CommandArgs(prefix, command, commandToRun, args);
    }

    /**
     * 获取指令前缀
     * @param text 消息
     * @return 是指令的话返回指令前缀, 不是指令的话返回""
     */
    private static String getPrefix(String text)
    {
        String defaultPrefix = CommandManager.getPrefix();

        if (text.startsWith(defaultPrefix)) return defaultPrefix;

        for (String prefix : Main.getConfig().getStringList("BotProperties.EnabledCommandPrefixes"))
            if (text.startsWith(prefix)) return prefix;

        return "";
    }
}
