package cc.moecraft.irc.osubot;

import cc.moecraft.irc.osubot.achievement.AchievementManager;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.command.CommandManager;
import cc.moecraft.irc.osubot.language.Messenger;
import cc.moecraft.irc.osubot.listener.CommandListener;
import cc.moecraft.irc.osubot.management.PermissionConfig;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuAPIWrapper;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.logger.DebugLogger;
import io.jboot.Jboot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 此类由 Hykilpikonna 在 2018/04/20 创建!
 * Created by Hykilpikonna on 2018/04/20!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class Main {
    // 配置/语言文件路径
    public static final String PATH = "src" + File.separator + "main" + File.separator + "resources"; // TODO: 这里分两个版本, 测试放现在这个路径, 发布的话放"./conf/"路径

    private static ArrayList<PircBotX> osuBots; // 机器人对象

    @Getter
    private static BotConfig config; // 配置文件 ( 用来存用于IRC登陆的账号密码 )

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
    private static boolean debug; // 是否开启测试

    @Getter @Setter
    private static boolean enableListening = true; // 是否监听消息

    @Getter
    private static int downloadMaxTries = 3; // 下载失败重试次数

    public static void main(String[] args) throws IOException, IrcException, InstantiationException, IllegalAccessException
    {
        // Jboot.run(args);
        logger = new DebugLogger("HyOsuIRCBot", true);

        config = new BotConfig();
        debug = config.getBoolean("BotProperties.DebugLogging");
        logger.setDebug(debug);

        // 创建对象
        osuBots = createBots(config.getAccounts());
        commandManager = new CommandManager();
        messenger = new Messenger();
        permissionConfig = new PermissionConfig();
        downloader = new DownloadUtils(config.getInt("BotProperties.Download.Timeout"));
        osuAPIUtils = new OsuAPIUtils(config.getString("BotProperties.Download.Osu.APIKey"), downloader);
        wrapper = new OsuAPIWrapper(osuAPIUtils);
        achievementManager = new AchievementManager();

        // 注册指令  优化: 2018-05-02
        registerAllCommands();

        // 连接服务器
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                50,                  //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
                100,            //线程池最大能容忍的线程数
                Long.MAX_VALUE,                    //线程存活时间
                TimeUnit.NANOSECONDS,              //参数keepAliveTime的时间单位
                new ArrayBlockingQueue<>(osuBots.size()) //任务缓存队列，用来存放等待执行的任务
        );

        startBots(osuBots, executor);
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

    /**
     * 创建多账号机器人列表
     * @param accounts 账号列表
     * @return 机器人列表
     */
    public static ArrayList<PircBotX> createBots(ArrayList<BotAccount> accounts)
    {
        ArrayList<PircBotX> osuBots = new ArrayList<>();

        String serverAddress = config.getString("ServerProperties.Address");
        int serverPort = config.getInt("ServerProperties.Port");
        List<String> autoJoinChannels = config.getStringList("BotProperties.AutoJoinChannels");

        for (BotAccount account : accounts)
        {
            Configuration botConfig = new Configuration.Builder()
                    .addServer(serverAddress, serverPort)
                    .addAutoJoinChannels(autoJoinChannels)
                    .addListener(new CommandListener(account.channel))
                    .setName(account.getUsername())
                    .setServerPassword(account.getPassword())
                    .buildConfiguration();

            osuBots.add(new PircBotX(botConfig));
        }

        return osuBots;
    }

    public static void startBots(ArrayList<PircBotX> bots, ThreadPoolExecutor executor)
    {
        for (PircBotX bot : bots)
        {
            executor.execute(new BotRunnable(bot));
            System.out.println("已启动: " + bot.toString());
        }
    }

    @AllArgsConstructor
    public static class BotRunnable implements Runnable
    {
        private PircBotX bot;

        @Override
        public void run()
        {
            try {
                bot.startBot();
            } catch (IOException | IrcException e) {
                e.printStackTrace();
                System.out.println("启动失败");
            }
        }
    }
}
