package cc.moecraft.irc.osubot;

import cc.moecraft.irc.osubot.command.CommandManager;
import cc.moecraft.irc.osubot.command.commands.CommandHelp;
import cc.moecraft.irc.osubot.command.commands.CommandPing;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.util.ArrayList;

/*
    有什么好的想法随时写在这里!

    ▷: 排队
    ▶: 开发中

    [ ]: 未完成
    [?]: 不确定
    [-]: 部分完成
    [D]: 需要测试
    [X]: 完成

    TODO: 以下所有...
    ▶ [ ] 注册个机器人账号 ( 还在等ppy回复邮件... )
    ▶ [-] 指令系统
       ▷ [D] 框架
       ▷ [ ] 帮助指令
       ▷ [ ] 列出所有指令的指令
    ▷ [ ] 用户系统
        ▷ [ ] 权限组
    ▷ [ ] 语言文件
        ▷ [ ] 用来回复消息的类
    ▷ [?] 后台log转发...?
 */

/**
 * 此类由 Hykilpikonna 在 2018/04/20 创建!
 * Created by Hykilpikonna on 2018/04/20!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class Main
{
    // 版本 ( 不懂怎样配置Github版本...
    public static final String VERSION = "0.0.1";

    // 配置文件 ( 用来存用于IRC登陆的账号密码 )
    private static BotConfig config;

    // 机器人对象
    private static PircBotX osuBot;

    // 指令管理器
    private static CommandManager commandManager;
    
    // Logger
    private static DebugLogger logger;
    
    // 是否开启测试
    private static boolean debug = true;

    public static void main(String[] args) throws IOException, IrcException
    {
        config = new BotConfig();

        BotProperties properties = new BotProperties()
                .setUsername(config.getUsername())
                .setIrcServerAddress(config.getString("ServerProperties.Address"))
                .setIrcServerPort(config.getInt("ServerProperties.Port"))
                .setIrcServerPassword(config.getPassword())
                .setBotAdminUsername(config.getAdminUsernames())
                .setAutoJoinChannels((ArrayList<String>) config.getStringList("BotProperties.AutoJoinChannels"));

        // 创建对象
        osuBot = new PircBotX(properties.toPircConfiguration());
        commandManager = new CommandManager();
        logger = new DebugLogger("HyOsuIRCBot", debug);

        // 注册指令 //TODO: 优化
        commandManager.registerCommand(new CommandHelp());
        commandManager.registerCommand(new CommandPing());

        // 连接服务器
        osuBot.startBot();

        // 想创建机器人的频道但是发现osu的irc服务器禁用了创建...
        // 这里用于在本地服务器测试
        // osuBot.joinChannel("#bots");
    }

    public static BotConfig getConfig()
    {
        return config;
    }

    public static PircBotX getOsuBot()
    {
        return osuBot;
    }

    public static CommandManager getCommandManager()
	{
        return commandManager;
    }

    public static DebugLogger getLogger()
	{
        return logger;
    }
}
