package cc.moecraft.test;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.command.Command;
import org.reflections.Reflections;

import java.util.Set;

/**
 * 此类由 Hykilpikonna 在 2018/05/02 创建!
 * Created by Hykilpikonna on 2018/05/02!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class ReflectionCommandRegisterTest
{
    private static DebugLogger logger = new DebugLogger("ReflectionCommandRegisterTest", true);

    public static void main(String[] args)
    {
        Reflections reflections = new Reflections("cc.moecraft.irc.osubot.command.commands");

        // 获取包下的所有继承Command的类
        Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);

        commands.forEach(command -> logger.debug("识别到的包: " + command.getName()));
    }
}
