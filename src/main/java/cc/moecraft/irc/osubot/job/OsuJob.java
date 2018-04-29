package cc.moecraft.irc.osubot.job;

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
        long maxId = DAOFactory.getOsuStdService().getMaxId();
        long nextId = maxId + 1;
        if(totalUser < nextId){
            stop();
        }else{
            DAOFactory.getOsuStdService().saveById(nextId);
        }
    }

    @Override
    public void stop() {
        logger.info("任务结束了");
    }
}
