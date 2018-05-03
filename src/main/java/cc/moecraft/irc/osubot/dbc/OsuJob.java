package cc.moecraft.irc.osubot.dbc;

import cc.moecraft.irc.osubot.factory.DAOFactory;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import com.jfinal.plugin.cron4j.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OsuJob implements ITask {

    private Logger logger = LoggerFactory.getLogger(OsuJob.class);

    private static long totalUser = Long.parseLong(PropertiesUtil.readKey("total_user"));


    @Override
    public void run() {
        try {
            long maxId = DAOFactory.getOsuStdService().getMaxId();
            long total = DAOFactory.getOsuStdService().totalCount();
            if (totalUser == total) {
                stop();
            } else if (0 == total) {
                //第一次执行插入数据库
                DAOFactory.getOsuStdService().asyncSave(totalUser, maxId + 1);
            } else if (totalUser > total) {
                long rId = DAOFactory.getOsuStdService().getRandomId();
                //线程执行中总会有失败的情况，第二次进来，从一个随机数，再来 totalUser - total 次
                long size = totalUser - total;
                DAOFactory.getOsuStdService().asyncSave(size, rId + 1);
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
