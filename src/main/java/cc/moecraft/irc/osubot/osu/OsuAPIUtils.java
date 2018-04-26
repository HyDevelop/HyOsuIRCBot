package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.irc.osubot.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;

/**
 * 此类由 Hykilpikonna 在 2018/04/23 创建!
 * Created by Hykilpikonna on 2018/04/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
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
     * @throws IllegalAccessException 反射失败
     */
    public DataBase get(ParametersBase parameter) throws IllegalAccessException
    {
        JSONObject jsonObject = getJSONObjectFromParameter(parameter);

        DataBase dataBase = parameter.dataStorageObject();

        // 反射赋值
        for (Field field : dataBase.getClass().getDeclaredFields())
        {
            assert jsonObject != null;
            if (jsonObject.containsKey(field.getName()))
            {
                // 赋值 ( 类型转换可能出错
                field.set(dataBase, jsonObject.get(field.getName()));
            }
            else
            {
                // 数据类声明的变量名和JSON类不匹配的可能性
                // TODO: 注入变量名...? ( 不知道能不能实现
            }
        }

        return dataBase;
    }

    /**
     * 用HTTP参数获取JSONObject
     *
     * @param parameter HTTP参数
     * @return JSON对象
     * @throws IllegalAccessException 反射失败
     */
    private JSONObject getJSONObjectFromParameter(ParametersBase parameter) throws IllegalAccessException
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
                    // TODO: 必要参数值是null的情况
                    return null;
                }
            }
        }

        System.out.println("获取到的URL请求: " + urlBuilder.toString());

        return downloader.getJSONObjectFromURL(urlBuilder.toString());
    }
}
