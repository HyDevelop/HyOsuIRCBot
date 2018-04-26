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
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import io.jboot.Jboot;
import lombok.Getter;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/20 创建!
 * Created by Hykilpikonna on 2018/04/20!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class Main {
    // 版本 ( 不懂怎样配置Github版本...
    public static final String VERSION = "0.0.2";

    // 配置/语言文件路径
    public static final String PATH = "src" + File.separator + "main" + File.separator + "resources"; //TODO: 这里分两个版本, 测试放现在这个路径, 发布的话放"./conf/"路径

    @Getter
    private static BotConfig config; // 配置文件 ( 用来存用于IRC登陆的账号密码 )

    @Getter
    private static PircBotX osuBot; // 机器人对象

    @Getter
    private static CommandManager commandManager; // 指令管理器

    @Getter
    private static DebugLogger logger; // Logger

    @Getter
    private static Messenger messenger; // 消息发送器

    @Getter
    private static PermissionConfig permissionConfig; // 权限配置文件

    @Getter
    private static DownloadUtils downloader; // 权限配置文件

    @Getter
    private static OsuAPIUtils osuAPIUtils;

    @Getter
    private static boolean debug = true; // 是否开启测试

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
        downloader = new DownloadUtils(config.getInt("BotProperties.Download.Timeout"));
        osuAPIUtils = new OsuAPIUtils(config.getString("BotProperties.Download.Osu.APIKey"), downloader);

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
        commandManager.registerCommand(new CommandRemoveAdmin());

        // 连接服务器
        osuBot.startBot();
    }
}
