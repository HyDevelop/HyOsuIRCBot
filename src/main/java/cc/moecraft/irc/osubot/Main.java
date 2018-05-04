package cc.moecraft.irc.osubot;

import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.command.CommandManager;
import cc.moecraft.irc.osubot.language.Messenger;
import cc.moecraft.irc.osubot.listener.CommandListener;
import cc.moecraft.irc.osubot.management.PermissionConfig;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuAPIWrapper;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import io.jboot.Jboot;
import lombok.Getter;
import lombok.Setter;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * 此类由 Hykilpikonna 在 2018/04/20 创建!
 * Created by Hykilpikonna on 2018/04/20!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class Main {
    public static final String VERSION = "0.0.5"; // 版本 ( 不懂怎样配置Github版本...

    // 配置/语言文件路径
    public static final String PATH = "src" + File.separator + "main" + File.separator + "resources"; // TODO: 这里分两个版本, 测试放现在这个路径, 发布的话放"./conf/"路径

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
    private static OsuAPIWrapper wrapper; // Osu官方API数据获取器 封装

    @Getter
    private static boolean debug; // 是否开启测试

    @Getter @Setter
    private static boolean enableListening = true; // 是否监听消息

    @Getter
    private static int downloadMaxTries = 3; // 下载失败重试次数

    public static void main(String[] args) throws IOException, IrcException, InstantiationException, IllegalAccessException
    {
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
        wrapper = new OsuAPIWrapper(osuAPIUtils);

        // 注册指令  优化: 2018-05-02
        registerAllCommands();

        // 连接服务器
        osuBot.startBot();
    }

    /**
     * 自动循环commands下的所有包找指令类
     * 然后反射实例注册
     */
    public static void registerAllCommands() throws IllegalAccessException, InstantiationException
    {
        Reflections reflections = new Reflections("cc.moecraft.irc.osubot.command.commands");

        // 获取包下的所有继承Command的类
        Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);

        // 循环注册
        for (Class<? extends Command> command : commands)
            commandManager.registerCommand(command.newInstance());
    }
}
