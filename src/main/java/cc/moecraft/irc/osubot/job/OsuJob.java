package cc.moecraft.irc.osubot.job;

import cc.moecraft.irc.osubot.common.Constant;
import cc.moecraft.irc.osubot.factory.DAOFactory;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import com.jfinal.plugin.cron4j.ITask;
import io.jboot.Jboot;
import io.jboot.component.redis.JbootRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OsuJob implements ITask {

    private Logger logger = LoggerFactory.getLogger(OsuJob.class);

    private static long totalUser = Long.parseLong(PropertiesUtil.readKey("total_user"));

    private JbootRedis redis = Jboot.me().getRedis();


    @Override
    public void run() {
        try {
            if(null == redis.get(Constant.OSU_LAST_TOTALS)){
                //第一次的话，直接就初始化那个11998017进去，以后爬完了等官网有新人数，可以对比Redis上一次一直记着这个
                redis.set(Constant.OSU_LAST_TOTALS,totalUser);
            }
            long lastTotal = Long.parseLong(redis.get(Constant.OSU_LAST_TOTALS));
            long maxId = DAOFactory.getOsuStdService().getMaxId();
            long total = DAOFactory.getOsuStdService().totalCount();
            if (totalUser == total) {
                stop();
            } else if (0 == total) {
                //第一次执行插入数据库
                DAOFactory.getOsuStdService().asyncSave(totalUser, maxId + 1);
            } else if (totalUser > lastTotal) {
                //有新的total了，开始爬新的
                long size = totalUser - lastTotal;
                DAOFactory.getOsuStdService().asyncSave(size, maxId + 1);
            }
        } catch (Exception ignored) {
            //这里抓到所有异常都忽略，免得频繁报错，导致日志刷屏
        }
    }

    @Override
    public void stop() {
        logger.info("已经爬完了...");
    }
}
