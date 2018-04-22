package cc.moecraft.irc.osubot;

import cc.moecraft.irc.osubot.command.CommandManager;
import cc.moecraft.irc.osubot.command.commands.CommandCommands;
import cc.moecraft.irc.osubot.command.commands.CommandHelp;
import cc.moecraft.irc.osubot.command.commands.fun.CommandEcho;
import cc.moecraft.irc.osubot.command.commands.fun.CommandNullpo;
import cc.moecraft.irc.osubot.command.commands.fun.CommandPing;
import cc.moecraft.irc.osubot.command.commands.fun.CommandTime;
import cc.moecraft.irc.osubot.command.commands.management.*;
import cc.moecraft.irc.osubot.language.Messenger;
import cc.moecraft.irc.osubot.management.PermissionConfig;
import io.jboot.Jboot;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
    有什么好的想法随时写在这里!

    ▷: 排队
    ▶: 开发中/完成

    [ ]: 未完成
    [?]: 不确定
    [-]: 部分完成
    [D]: 需要测试
    [X]: 完成
    [#]: 取消

    TODO: 添加功能:
    ▶ [#] 注册个机器人账号 ( 被ppy拒绝 )
    ▶ [-] 指令系统
        ▶ [D] 框架
            ▶ [X] 基础框架
            ▶ [D] 检测权限 ( 需要权限组 )
        ▶ [-] 帮助指令
            ▶ [X] 帮助指令
            ▷ [ ] 帮助内容 ( 需要语言文件 )
        ▶ [X] 列出所有指令的指令
        ▷ [ ] 查询玩家信息的指令
    ▶ [-] 用户系统
        ▶ [?] 数据库
        ▶ [-] 权限组
            ▶ [X] 框架
            ▶ [D] 应用 ( 需要获取用户 )
        ▶ [D] 获取用户
        ▷ [ ] 管理服务器 ( 同时连两个服务器, 一个是Osu, 另一个是私服用来管理 )
        ▷ [ ] 检测新用户
            ▷ [ ] 新用户提示设置语言
    ▶ [-] 语言文件
        ▶ [X] 读取语言文件
        ▷ [?] 优化语言文件
        ▷ [ ] 分用户设置语言
            ▷ [ ] 检测Osu客户端语言
        ▷ [-] 用来回复消息的类
            ▷ [ ] 放在用户下直接用语言文件码回复消息
    ▷ [?] 后台log转发...?

    TODO: 修复Bug:
    ▶ [X] 登陆/退出消息刷屏
 */

/**
 * 此类由 Hykilpikonna 在 2018/04/20 创建!
 * Created by Hykilpikonna on 2018/04/20!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class Main {
    // 版本 ( 不懂怎样配置Github版本...
    public static final String VERSION = "0.0.1";

    // 配置/语言文件路径
    public static final String PATH = "src" + File.separator + "main" + File.separator + "resources"; //TODO: 这里分两个版本, 测试放现在这个路径, 发布的话放"./conf/"路径

    // 配置文件 ( 用来存用于IRC登陆的账号密码 )
    private static BotConfig config;

    // 机器人对象
    private static PircBotX osuBot;

    // 指令管理器
    private static CommandManager commandManager;

    // Logger
    private static DebugLogger logger;

    // 消息发送器
    private static Messenger messenger;

    // 权限配置文件
    private static PermissionConfig permissionConfig;

    // 是否开启测试
    private static boolean debug = true;

    public static void main(String[] args) throws IOException, IrcException {
        Jboot.run(args);

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
        messenger = new Messenger();
        permissionConfig = new PermissionConfig();

        // 注册指令 //TODO: 优化
        commandManager.registerCommand(new CommandHelp());
        commandManager.registerCommand(new CommandPing());
        commandManager.registerCommand(new CommandCommands());
        commandManager.registerCommand(new CommandTime());
        commandManager.registerCommand(new CommandNullpo());
        commandManager.registerCommand(new CommandReload());
        commandManager.registerCommand(new CommandRestart());
        commandManager.registerCommand(new CommandGroups());
        commandManager.registerCommand(new CommandEcho());
        commandManager.registerCommand(new CommandUserPermission());
        commandManager.registerCommand(new CommandSetAdmin());

        // 连接服务器
        osuBot.startBot();
    }

    public static BotConfig getConfig() {
        return config;
    }

    public static PircBotX getOsuBot() {
        return osuBot;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    public static DebugLogger getLogger() {
        return logger;
    }

    public static Messenger getMessenger() {
        return messenger;
    }

    public static PermissionConfig getPermissionConfig()
    {
        return permissionConfig;
    }
}
