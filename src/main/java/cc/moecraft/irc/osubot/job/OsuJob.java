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
            long maxId;
            if(null != redis.get(Constant.USER_MAX_ID)){
                maxId = Long.parseLong(redis.get(Constant.USER_MAX_ID).toString());
            }else{
                maxId = DAOFactory.getOsuStdService().getMaxId();
            }
            long nextId = maxId + 1;
            if(totalUser < nextId){
                stop();
            }else{
                DAOFactory.getOsuStdService().asyncSaveById();
            }
        } catch (Exception ignored) {
            //这里抓到所有异常都忽略，免得频繁报错，导致日志刷屏
        }
    }

    @Override
    public void stop() {
        logger.info("任务结束了");
    }
}
