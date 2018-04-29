package cc.moecraft.irc.osubot.service.impl;

import cc.moecraft.irc.osubot.common.Constant;
import cc.moecraft.irc.osubot.model.OsuStd;
import cc.moecraft.irc.osubot.osu.Api;
import cc.moecraft.irc.osubot.service.OsuStdService;
import cc.moecraft.irc.osubot.utils.JsonUtils;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import com.google.gson.JsonElement;
import com.jfinal.kit.StrKit;
import io.jboot.Jboot;
import io.jboot.component.redis.JbootRedis;
import io.jboot.utils.ArrayUtils;

import java.util.List;
import java.util.Map;

public class OsuStdServiceImpl implements OsuStdService {

    private JbootRedis redis = Jboot.me().getRedis();

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
    public void saveById(long nextId) {
        final String key = PropertiesUtil.readKey("osu_key");
        final String url = Api.User.apiUrl;
        //定时任务是一分钟，而子线程开启等待时间设置60秒
        Thread mThreadClient = new Thread(() -> {
            for (int j = 0; j < 100; j++) {
                String json = Jboot.httpPost(url + "?k=" + key + "&type=id&u=" + (nextId + j));
                if(!StrKit.isBlank(json)){
                    JsonElement jsonElement = JsonUtils.parseJsonElement(json);
                    if(jsonElement.isJsonArray()){
                        List<Map> list = JsonUtils.getArrayByJson(json, Map.class);
                        if(ArrayUtils.isNotEmpty(list)){
                            String userId = list.get(0).get("user_id").toString();
                            if(String.valueOf(nextId + j).equalsIgnoreCase(userId)){
                                redis.set(Constant.USER_MAX_ID,String.valueOf(nextId + j));
                                if(ArrayUtils.isNotEmpty(list) && !getExistById(nextId + j)){
                                    String jsonString = JsonUtils.toJsonString(list.get(0));
                                    OsuStd osuStd = JsonUtils.jsonToModel(jsonString, OsuStd.class);
                                    osuStd.save();
                                }
                            }
                        }
                    }
                }
            }
        });
        mThreadClient.start();
        //等待子线程执行完毕
        try {
            mThreadClient.join(60000);
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public boolean getExistById(long userId) {
        String sql = "select count(*) as total from osu_std where user_id = "+ userId;
        return osuStdDao.findFirst(sql).getLong("total") > 0;
    }
}
