package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.JsonUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.irc.osubot.utils.StringUtils;
import com.google.gson.*;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 此类由 Hykilpikonna 在 2018/04/23 创建!
 * Created by Hykilpikonna on 2018/04/23!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
@AllArgsConstructor
public class OsuAPIUtils
{
    public static final String BASE_URL = "http://osu.ppy.sh/api/get_";

    private String apiKey;

    private DownloadUtils downloader;

    /**
     * 判断一个用户名在Osu官方数据库里是否存在
     * @param username 用户名
     * @return 是否存在
     */
    public boolean isUserExisting(String username) throws IllegalAccessException
    {
        JsonArray array = getJsonElementFromParameter(UserParameters.builder().u(username).build()).getAsJsonArray();

        return !(array.size() == 0);
    }

    /**
     * 用HTTP参数直接获取数据类对象
     * @param parameter HTTP参数
     * @return 数据类对象
     * @throws IllegalAccessException 没有权限访问反射到的变量
     */
    public ArrayList<DataBase> get(ParametersBase parameter) throws IllegalAccessException
    {
        JsonElement jsonElement = getJsonElementFromParameter(parameter);
        assert jsonElement != null;

        JsonArray jsonArray;

        // 在Osu官方API获取的话可能获取到Array
        if (jsonElement.isJsonArray()) jsonArray = jsonElement.getAsJsonArray();
        else if (jsonElement.isJsonObject()) jsonArray = JsonUtils.toJsonArray(jsonElement);
        else
        {
            // 既不是JsonArray也不是JsonObject的情况
            return null;
        }

        ArrayList<DataBase> data = new ArrayList<>();

        // 赋值
        for (JsonElement element : jsonArray)
        {
            data.add(new Gson().fromJson(element, (Type) parameter.dataStorageClass()));
        }

        return data;
    }

    /**
     * 赋值助手 ( 考虑到可能需要递归就分开写了 )
     *
     * @param parameter HTTP参数
     * @param dataBaseList 数据类存储对象列表
     * @param jsonArray Json对象列表
     * @throws IllegalAccessException 没有权限访问反射到的变量
     * @throws InstantiationException 反射创建实例失败
     * @throws InvocationTargetException 反射方法激活失败
     */
    @Deprecated
    private void assignHelper(ParametersBase parameter, ArrayList<DataBase> dataBaseList, JsonArray jsonArray) throws IllegalAccessException, InstantiationException, InvocationTargetException
    {
        for (int i = 0; i < jsonArray.size(); i++)
        {
            JsonObject element = jsonArray.get(i).getAsJsonObject(); // TODO: 如果两个Array套在一起就会出错

            // 反射添加新的实例
            dataBaseList.add((DataBase) parameter.dataStorageClass().newInstance());
            DataBase data = dataBaseList.get(i);

            // 反射赋值
            assignHelper2(data, element);
        }
    }

    /**
     * 赋值递归助手 ( 考虑到可能需要递归就分开写了 )
     *
     * @param object 赋值对象
     * @param element JSON对象
     * @throws IllegalAccessException 没有权限访问反射到的变量
     * @throws InvocationTargetException 反射方法激活失败
     */
    @Deprecated
    public void assignHelper2(Object object, JsonObject element) throws InvocationTargetException, IllegalAccessException
    {
        for (Field field : object.getClass().getDeclaredFields())
        {
            if (element.keySet().contains(field.getName()))
            {
                // 赋值 ( 类型转换可能出错
                field.setAccessible(true);

                // 如果是基础类, 反射获取getAs方法转换
                if (ReflectUtils.isPrimitive(field.getType()))
                {
                    JsonPrimitive primitive = element.get(field.getName()).getAsJsonPrimitive();

                    field.set(object, Objects.requireNonNull(ReflectUtils.getJsonPrimitiveGetAsMethod(field, primitive)).invoke(primitive));
                }
                else
                {
                    // 不是基础类, 递归
                    System.out.println("递归: " + field.getType().getSimpleName());
                    assignHelper2(field.get(object), (JsonObject) element.get(field.getName()));
                }
            }
        }
    }

    /**
     * 用HTTP参数获取JSONObject
     *
     * @param parameter HTTP参数
     * @return JSON对象
     * @throws IllegalAccessException 反射失败
     */
    public JsonElement getJsonElementFromParameter(ParametersBase parameter) throws IllegalAccessException
    {
        // 获取URL
        StringBuilder urlBuilder = new StringBuilder();

        boolean completeURL = parameter.subURL().contains("%COMPLETE_URL%");

        urlBuilder.append(completeURL ? parameter.subURL().replace("%COMPLETE_URL%", "") + "?" : BASE_URL + parameter.subURL() + "?");

        // 添加 APIKey
        if (!completeURL) StringUtils.addParameter(urlBuilder, "k", apiKey);

        for (Field field : parameter.getClass().getDeclaredFields())
        {
            if (field.isAnnotationPresent(HttpParameter.class))
            {
                boolean required = field.getAnnotation(HttpParameter.class).required();

                Object value = ReflectUtils.getValue(field, parameter);

                if (value != null)
                {
                    // 添加参数
                    StringUtils.addParameter(urlBuilder, field.getName(), value.toString());
                }
                else if (required)
                {
                    // 必要参数值是null的情况
                    return null;
                }
            }
        }

        return downloader.getJsonElementFromURL(urlBuilder.toString());
    }

    /**
     * 获取用户输入的模式的数字形式
     * 如果没找到返回-1
     *
     * @param mode 模式名
     * @return 模式
     */
    public static int getModeWithName(String mode)
    {
        mode = mode.toLowerCase();

        Map<String, Integer> modeMap = new HashMap<>();

        modeMap.put("std", 0);
        modeMap.put("standard", 0);
        modeMap.put("s", 0);
        modeMap.put("taiko", 1);
        modeMap.put("t", 1);
        modeMap.put("ctb", 2);
        modeMap.put("catch", 2);
        modeMap.put("catchthebrat", 2);
        modeMap.put("c", 2);
        modeMap.put("mania", 3);
        modeMap.put("m", 3);

        return modeMap.getOrDefault(mode, -1);
    }

    /**
     * 获取模式的名字
     * 如果没找到返回null
     *
     * @param mode 模式
     * @return 模式名
     */
    public static String getModeNameWithMode(int mode)
    {
        Map<Integer, String> modeMap = new HashMap<>();

        modeMap.put(0, "STD");
        modeMap.put(1, "Taiko");
        modeMap.put(2, "CTB");
        modeMap.put(3, "Mania");

        return modeMap.getOrDefault(mode, null);
    }
}
