package com.dullwolf;


import cc.moecraft.irc.osubot.common.Constant;
import cc.moecraft.irc.osubot.factory.DAOFactory;
import io.jboot.Jboot;
import io.jboot.component.redis.JbootRedis;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {

    private static JbootRedis redis = Jboot.me().getRedis();

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            long maxId;
            if(null != redis.get("testCall")){
                maxId = Long.parseLong(redis.get("testCall").toString());
            }else{
                maxId = 0;
            }
            System.out.println("当前值是：" + maxId);
            long nextId = maxId + 1;
            Callable<Long> callable = () -> nextId;
            FutureTask<Long> future = new FutureTask<>(callable);
            new Thread(future).start();
            try {
                System.out.println("处理后的值是：" + future.get());
                //然后放进缓存
                redis.set("testCall",String.valueOf(future.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
