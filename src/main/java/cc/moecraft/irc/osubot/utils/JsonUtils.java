package cc.moecraft.irc.osubot.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/58/26 创建!
 * Created by Hykilpikonna on 2018/58/26!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class JsonUtils {

    /**
     * 把JSON字符串转换为Java对象
     *
     * @param json json字符串
     * @param targetClass 对象类
     * @param <T> 泛型
     * @return Java对象
     */
    public static <T> T getObjectByJson(String json, Class<T> targetClass)
    {
        return new Gson().fromJson(json, targetClass);
    }

    /**
     * 把JSON字符串转换成JsonElement对象
     *
     * @param json JSON字符串
     * @return 转换后的JSONObject
     */
    public static JsonElement parseJsonElement(String json)
    {
        return new JsonParser().parse(json);
    }

    /**
     * 解析对象转换为json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String toJsonString(Object obj)
    {
        return new Gson().toJson(obj);
    }

    /**
     * 把对象直接转换为JSONObject
     *
     * @param obj 目标对象
     * @return 转换后的JSONObject
     */
    public static JsonElement toJsonElement(Object obj)
    {
        return parseJsonElement(toJsonString(obj));
    }

    /**
     * 把JSONObject转换为JsonArray的一项
     *
     * @param jsonElement Json对象
     * @return JsonArray
     */
    public static JsonArray toJsonArray(JsonElement jsonElement)
    {
        JsonArray result = new JsonArray();

        result.add(jsonElement);

        return result;
    }

    /**
     * 把JSON字符串转换为Model对象（只针对数据库Model所用，其他情况别乱用）
     *
     */
    public static <T extends Model> T jsonToModel(String json, Class<T> t) {
        T model = null;
        if(!StrKit.isBlank(json)){
            try {
                Map map = getObjectByJson(json, Map.class);
                model = t.newInstance();
                model.put(map);
                model.put("_auto_copy_model_",true);
                //model.put(ModelCopier.MODEL_FROM_COPIER, true);
            } catch (Exception ignored) {
                //可忽略
            }
        }
        return model;
    }

    /**
     * 解析json字符串 转换指定的列表对象 (有时候我还是想要List<Map>集合，这个保留)
     * @param json json字符串
     * @param clazz 列表对象
     * @param <T> 泛型
     * @return
     */
    public static <T> List<T> getArrayByJson(String json, Class<T> clazz){
        return JSON.parseArray(json, clazz);
    }
}
