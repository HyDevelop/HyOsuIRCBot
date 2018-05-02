package cc.moecraft.irc.osubot.command.commands.management.power;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandRestart extends Command
{
    public CommandRestart()
    {
        super("restart");
    }

    /**
     * 重启指令, 但是不知道什么原因在IDEA的运行界面没办法重启...
     * TODO: 测试这个
     *
     * @param event 事件
     * @param sender 发送者的irc用户 ( 在osu的irc服务器的话用户名就是osu的用户名 )
     * @param channel 频道
     * @param command 指令名 ( 不包含指令参数 )
     * @param args 指令参数 ( 不包含指令名 )
     */
    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        try
        {
            Main.getMessenger().respond(event, "已执行重启命令, 重启成功不会发送通知, 重启失败会报错");
            restartApplication(null);
            // 这里执行不到, 因为进程已经结束了
        }
        catch (IOException e)
        {
            Main.getMessenger().respond(event, "重启命令执行失败, 报错请看后台");
            e.printStackTrace();
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.managing.restart";
    }

    /**
     * Sun property pointing the main class and its arguments.
     * Might not be defined on non Hotspot VM implementations.
     */
    public static final String SUN_JAVA_COMMAND = "sun.java.command";

    /**
     * Restart the current Java application
     * @param runBeforeRestart some custom code to be run before restarting
     * @throws IOException
     */
    public static void restartApplication(Runnable runBeforeRestart) throws IOException
    {
        try
        {
            String java = System.getProperty("java.home") + "/bin/java";
            List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
            StringBuilder vmArgsOneLine = new StringBuilder();

            for (String arg : vmArguments)
            {
                if (!arg.contains("-agentlib"))
                {
                    vmArgsOneLine.append(arg);
                    vmArgsOneLine.append(" ");
                }
            }
            final StringBuffer cmd = new StringBuffer("\"" + java + "\" " + vmArgsOneLine);
            String[] mainCommand = System.getProperty(SUN_JAVA_COMMAND).split(" ");

            if (mainCommand[0].endsWith(".jar")) cmd.append("-jar ").append(new File(mainCommand[0]).getPath());
            else cmd.append("-cp \"").append(System.getProperty("java.class.path")).append("\" ").append(mainCommand[0]);

            for (int i = 1; i < mainCommand.length; i++)
            {
                cmd.append(" ");
                cmd.append(mainCommand[i]);
            }

            Runtime.getRuntime().addShutdownHook(new Thread(() ->
            {
                try
                {
                    Runtime.getRuntime().exec(cmd.toString());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }));

            if (runBeforeRestart != null) runBeforeRestart.run();

            System.exit(0);
        }
        catch (Exception e)
        {
            throw new IOException("Error while trying to restart the application", e);
        }
    }
}
