package cc.moecraft.irc.osubot;

import cc.moecraft.irc.osubot.command.CommandManager;
import cc.moecraft.irc.osubot.command.commands.CommandCommands;
import cc.moecraft.irc.osubot.command.commands.CommandHelp;
import cc.moecraft.irc.osubot.command.commands.fun.CommandEcho;
import cc.moecraft.irc.osubot.command.commands.fun.CommandNullpo;
import cc.moecraft.irc.osubot.command.commands.fun.CommandPing;
import cc.moecraft.irc.osubot.command.commands.fun.CommandTime;
import cc.moecraft.irc.osubot.command.commands.management.*;
import cc.moecraft.irc.osubot.command.commands.osu.CommandStats;
import cc.moecraft.irc.osubot.language.Messenger;
import cc.moecraft.irc.osubot.listener.CommandListener;
import cc.moecraft.irc.osubot.management.PermissionConfig;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.yaml.Config;
import io.jboot.Jboot;
import lombok.Getter;
import org.pircbotx.Configuration;
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
    public static final String VERSION = "0.0.4";

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
    private static DownloadUtils downloader; // 下载器

    @Getter
    private static OsuAPIUtils osuAPIUtils; // Osu官方API数据获取器

    @Getter
    private static boolean debug; // 是否开启测试

    public static void main(String[] args) throws IOException, IrcException {
        Jboot.run(args);

        config = new BotConfig();
        debug = config.getBoolean("BotProperties.DebugLogging");

        Configuration botConfig = new Configuration.Builder()
                .setName(config.getUsername())
                .addServer(config.getString("ServerProperties.Address"), config.getInt("ServerProperties.Port"))
                .setServerPassword(config.getPassword())
                .addAutoJoinChannels(config.getStringList("BotProperties.AutoJoinChannels"))
                .addListener(new CommandListener())
                .buildConfiguration();

        // 创建对象
        osuBot = new PircBotX(botConfig);
        commandManager = new CommandManager();
        logger = new DebugLogger("HyOsuIRCBot", true);
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
        commandManager.registerCommand(new CommandStats());

        // 连接服务器
        osuBot.startBot();
    }
}
