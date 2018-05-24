package cc.moecraft.irc.osubot;

import cc.moecraft.irc.osubot.osu.achievement.AchievementManager;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.command.CommandManager;
import cc.moecraft.irc.osubot.language.LanguageFileManager;
import cc.moecraft.irc.osubot.language.Messenger;
import cc.moecraft.irc.osubot.management.PermissionConfig;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuAPIWrapper;
import cc.moecraft.irc.osubot.osu.OsuHtmlUtils;
import cc.moecraft.irc.osubot.platforms.irc.IrcConfig;
import cc.moecraft.irc.osubot.platforms.irc.IrcManager;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.logger.DebugLogger;
import lombok.Getter;
import lombok.Setter;
import org.reflections.Reflections;

import java.io.File;
import java.util.*;

/**
 * 此类由 Hykilpikonna 在 2018/04/20 创建!
 * Created by Hykilpikonna on 2018/04/20!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class Main {
    // 配置/语言文件路径
    public static final String PATH = "src" + File.separator + "main" + File.separator + "resources"; // TODO: 这里分两个版本, 测试放现在这个路径, 发布的话放"./conf/"路径

    @Getter
    private static IrcManager ircBotsManager; // 机器人管理器

    @Getter
    private static IrcConfig ircConfig; // IRC配置文件

    @Getter
    private static GeneralConfig generalConfig; // 其他配置文件

    @Getter
    private static CommandManager commandManager; // 指令管理器

    @Getter
    private static AchievementManager achievementManager; // 成就管理器

    @Getter @Setter
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
    private static OsuHtmlUtils osuHtmlUtils; // Osu官网HTML数据获取器

    @Getter
    private static boolean debug; // 是否开启测试

    @Getter @Setter
    private static boolean enableListening = true; // 是否监听消息

    @Getter
    private static int downloadMaxTries = 3; // 下载失败重试次数

    public static void main(String[] args) throws InstantiationException, IllegalAccessException
    {
        // Jboot.run(args);
        logger = new DebugLogger("HyOsuIRCBot", true);

        ircConfig = new IrcConfig();
        generalConfig = new GeneralConfig();
        debug = generalConfig.getBoolean("BotProperties.DebugLogging");
        logger.setDebug(debug);

        // 创建对象
        commandManager = new CommandManager();
        messenger = new Messenger(new LanguageFileManager(), getGlobalVariablesForMLT());
        permissionConfig = new PermissionConfig();
        downloader = new DownloadUtils(generalConfig.getInt("BotProperties.Download.Timeout"));
        osuAPIUtils = new OsuAPIUtils(generalConfig.getString("BotProperties.Download.Osu.APIKey"), downloader);
        wrapper = new OsuAPIWrapper(osuAPIUtils);
        osuHtmlUtils = new OsuHtmlUtils(downloader);
        achievementManager = new AchievementManager();

        // 注册指令  优化: 2018-05-02
        registerAllCommands();

        ircBotsManager = new IrcManager(ircConfig);
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

    public static Map<String, String> getGlobalVariablesForMLT()
    {
        Map<String, String> globalVariables = new HashMap<>();

        globalVariables.put("%prefix%", CommandManager.getPrefix());

        return globalVariables;
    }
}
