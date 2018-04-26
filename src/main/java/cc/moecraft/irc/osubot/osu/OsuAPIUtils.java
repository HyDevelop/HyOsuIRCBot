package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.JsonUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.irc.osubot.utils.StringUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;

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
     * 用HTTP参数直接获取数据类对象
     * @param parameter HTTP参数
     * @return 数据类对象
     * @throws IllegalAccessException 没有权限访问反射到的变量
     * @throws InstantiationException 反射创建实例失败
     */
    public ArrayList<DataBase> get(ParametersBase parameter) throws IllegalAccessException, InstantiationException
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
        assignHelper(parameter, data, jsonArray);

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
     */
    private void assignHelper(ParametersBase parameter, ArrayList<DataBase> dataBaseList, JsonArray jsonArray) throws IllegalAccessException, InstantiationException
    {
        for (int i = 0; i < jsonArray.size(); i++)
        {
            JsonObject element = jsonArray.get(i).getAsJsonObject(); // TODO: 如果两个Array套在一起就会出错

            // 反射添加新的实例
            dataBaseList.add((DataBase) parameter.dataStorageClass().newInstance());
            DataBase data = dataBaseList.get(i);

            // 反射赋值
            for (Field field : data.getClass().getDeclaredFields())
            {
                if (element.keySet().contains(field.getName()))
                {
                    // 赋值 ( 类型转换可能出错
                    field.setAccessible(true);

                    // 如果是基础类, 反射获取getAs方法转换
                    if (ReflectUtils.isPrimitive(field.getType()))
                    {
                        field.set(data, ReflectUtils.getJsonPrimitiveGetAsMethod(field, element.get(field.getName()).getAsJsonPrimitive()));
                    }
                    else
                    {
                        // TODO: 不是基础类的话怎么办呢...?
                        System.out.println("不支持类型: " + field.getType().getSimpleName());
                    }
                }
                else
                {
                    // 数据类声明的变量名和JSON类不匹配的可能性
                    // 注入变量名...? ( 不知道能不能实现
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

        System.out.println("获取到的URL请求: " + urlBuilder.toString());

        return downloader.getJsonElementFromURL(urlBuilder.toString());
    }
}
