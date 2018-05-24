package cc.moecraft.irc.osubot.platforms.irc;

import cc.moecraft.irc.osubot.GeneralConfig;
import cc.moecraft.irc.osubot.platforms.irc.listener.IrcCommandListener;
import cc.moecraft.irc.osubot.platforms.irc.listener.IrcExceptionListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 此类由 Hykilpikonna 在 2018/05/23 创建!
 * Created by Hykilpikonna on 2018/05/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data
public class IrcManager
{
    private IrcConfig config;
    private ArrayList<PircBotX> osuBots; // 机器人对象

    public IrcManager(IrcConfig config)
    {
        this.config = config;
        osuBots = createBots(config.getAccounts());

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
     * 创建多账号机器人列表
     * @param accounts 账号列表
     * @return 机器人列表
     */
    public ArrayList<PircBotX> createBots(ArrayList<IrcAccount> accounts)
    {
        ArrayList<PircBotX> osuBots = new ArrayList<>();

        String serverAddress = config.getString("ServerProperties.Address");
        int serverPort = config.getInt("ServerProperties.Port");
        List<String> autoJoinChannels = config.getStringList("BotProperties.AutoJoinChannels");

        for (IrcAccount account : accounts)
        {
            Configuration botConfig = new Configuration.Builder()
                    .addServer(serverAddress, serverPort)
                    .addAutoJoinChannels(autoJoinChannels)
                    .addListener(new IrcCommandListener(account.isChannel()))
                    .addListener(new IrcExceptionListener())
                    .setName(account.getUsername())
                    .setServerPassword(account.getPassword())
                    .buildConfiguration();

            osuBots.add(new PircBotX(botConfig));
        }

        return osuBots;
    }

    /**
     * 启动机器人
     * @param bots 机器人列表
     * @param executor 线程执行器
     */
    public void startBots(ArrayList<PircBotX> bots, ThreadPoolExecutor executor)
    {
        for (PircBotX bot : bots) executor.execute(new BotRunnable(bot));
    }

    @AllArgsConstructor
    public class BotRunnable implements Runnable
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
