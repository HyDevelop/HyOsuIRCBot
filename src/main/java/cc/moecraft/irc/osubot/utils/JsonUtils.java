package cc.moecraft.irc.osubot.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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
    /*
    /**
     * 解析json字符串 转换指定的列表对象
     * @param json json字符串
     * @param targetClass 列表对象
     * @param <T> 泛型
     * @return
     *//*
    public static <T> List<T> getArrayByJson(String json, Class<T> targetClass)
    {
        return .parseArray(json, targetClass);
    }

    /**
     * json字符串中下划线转为驼峰
     * @param json json字符串
     * @return 驼峰对象
     *//*
    public static Object convert(String json) {
        Object obj = JSON.parse(json);
        convert(obj);
        return obj;
    }

    private static void convert(Object json) {
        if (json instanceof JSONArray) {
            JSONArray arr = (JSONArray) json;
            for (Object obj : arr) {
                convert(obj);
            }
        } else if (json instanceof JSONObject) {
            JSONObject jo = (JSONObject) json;
            Set<String> keys = jo.keySet();
            String[] array = keys.toArray(new String[keys.size()]);
            for (String key : array) {
                Object value = jo.get(key);
                jo.remove(key);
                jo.put(StrKit.toCamelCase(key), value);
                convert(value);
            }
        }
    }*/
}
