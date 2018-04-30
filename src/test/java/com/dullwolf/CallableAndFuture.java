package com.dullwolf;


import cc.moecraft.irc.osubot.common.Constant;
import cc.moecraft.irc.osubot.factory.DAOFactory;
import cc.moecraft.irc.osubot.model.OsuStd;
import cc.moecraft.irc.osubot.osu.Api;
import cc.moecraft.irc.osubot.utils.JsonUtils;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import com.google.gson.JsonElement;
import com.jfinal.kit.StrKit;
import io.jboot.Jboot;
import io.jboot.component.redis.JbootRedis;
import io.jboot.utils.ArrayUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {

    private static JbootRedis redis = Jboot.me().getRedis();

    public static void main(String[] args) {
        final String key = PropertiesUtil.readKey("osu_key");
        final String url = Api.User.apiUrl;
        for (int i = 0; i < 500; i++) {
            long maxId;
            if(null != redis.get("testCall")){
                maxId = Long.parseLong(redis.get("testCall").toString());
            }else{
                maxId = 0;
            }
            long nextId = maxId + 1;
            Callable<Long> callable = () -> {
                String json = Jboot.httpPost(url + "?k=" + key + "&type=id&u=" + nextId);
                if(!StrKit.isBlank(json)){
                    JsonElement jsonElement = JsonUtils.parseJsonElement(json);
                    if(jsonElement.isJsonArray()){
                        List<Map> list = JsonUtils.getArrayByJson(json, Map.class);
                        if(ArrayUtils.isNotEmpty(list)){
                            String userId = list.get(0).get("user_id").toString();
                            if(String.valueOf(nextId).equals(userId) && ArrayUtils.isNotEmpty(list)){
                                System.out.println(userId+" 处理成功");
                            }
                        }
                    }
                }
                return nextId;
            };
            FutureTask<Long> future = new FutureTask<>(callable);
            new Thread(future).start();
            try {
                System.out.println("当前返回值是：" + future.get());
                //然后放进缓存
                redis.set("testCall",String.valueOf(future.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
