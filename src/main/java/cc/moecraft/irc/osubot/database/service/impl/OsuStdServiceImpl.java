package cc.moecraft.irc.osubot.database.service.impl;

import cc.moecraft.irc.osubot.dbc.MyTask;
import cc.moecraft.irc.osubot.database.model.OsuStd;
import cc.moecraft.irc.osubot.database.service.OsuStdService;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OsuStdServiceImpl implements OsuStdService {

    private OsuStd osuStdDao = new OsuStd();

    @Override
    public List<OsuStd> getAll() {
        return osuStdDao.findAll();
    }

    @Override
    public long getMaxId() {
        String sql = "select ifnull(max(user_id),0) as userId from osu_std ";
        return Long.parseLong(osuStdDao.findFirst(sql).getStr("userId"));
    }

    @Override
    public long totalCount() {
        String sql = "select count(*) as total from osu_std ";
        return osuStdDao.findFirst(sql).getLong("total");
    }

    @Override
    public boolean getExistById(long userId) {
        String sql = "select count(*) as total from osu_std where user_id = " + userId;
        return osuStdDao.findFirst(sql).getLong("total") > 0;
    }

    @Override
    public void asyncSave(long size,long nextId) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                50,  //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
                100, //线程池最大能容忍的线程数
                200, //线程存活时间
                TimeUnit.MILLISECONDS, //参数keepAliveTime的时间单位
                new ArrayBlockingQueue<>(50) //任务缓存队列，用来存放等待执行的任务
        );
        for (long i = 0; i < size; i++) {
            MyTask myTask = new MyTask(i + nextId);
            executor.execute(myTask);
        }
        executor.shutdown();

    }

    @Override
    public long getRandomId() {
        String sql = "select user_id from osu_std order by rand() limit " + totalCount();
        return osuStdDao.findFirst(sql).getUserId();
    }
}
