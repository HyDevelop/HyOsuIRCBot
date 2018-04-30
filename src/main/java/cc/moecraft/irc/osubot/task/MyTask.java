package cc.moecraft.irc.osubot.task;

import cc.moecraft.irc.osubot.factory.DAOFactory;
import cc.moecraft.irc.osubot.model.OsuStd;
import cc.moecraft.irc.osubot.osu.Api;
import cc.moecraft.irc.osubot.utils.JsonUtils;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import com.google.gson.JsonElement;
import com.jfinal.kit.StrKit;
import io.jboot.Jboot;
import io.jboot.utils.ArrayUtils;

import java.util.List;
import java.util.Map;

public class MyTask implements Runnable{

    private final String key = PropertiesUtil.readKey("osu_key");
    private final String url = Api.User.apiUrl;

    private long nextId;

    public MyTask(long nextId) {
        this.nextId = nextId;
    }

    @Override
    public void run() {
        String json = Jboot.httpPost(url + "?k=" + key + "&type=id&u=" + nextId);
        if(!StrKit.isBlank(json)){
            JsonElement jsonElement = JsonUtils.parseJsonElement(json);
            if(jsonElement.isJsonArray()){
                List<Map> list = JsonUtils.getArrayByJson(json, Map.class);
                if(ArrayUtils.isNotEmpty(list)){
                    String userId = list.get(0).get("user_id").toString();
                    if(String.valueOf(nextId).equals(userId) && ArrayUtils.isNotEmpty(list)){
                        if(!DAOFactory.getOsuStdService().getExistById(nextId)){
                            String jsonString = JsonUtils.toJsonString(list.get(0));
                            OsuStd osuStd = JsonUtils.jsonToModel(jsonString, OsuStd.class);
                            osuStd.save();
                        }
                    }
                }
            }
        }
    }

}
