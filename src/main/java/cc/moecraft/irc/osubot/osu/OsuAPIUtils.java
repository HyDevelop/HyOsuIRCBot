package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.JsonUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.irc.osubot.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;

/**
 * 此类由 Hykilpikonna 在 2018/04/23 创建!
 * Created by Hykilpikonna on 2018/04/23!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class OsuAPIUtils
{
    public static final String BASE_URL = "http://osu.ppy.sh/api/get_";

    /**
     * 用HTTP参数获取JSONObject
     *
     * @param parameter HTTP参数
     * @return JSON对象
     * @throws IllegalAccessException 反射失败
     */
    public static JSONObject getJSONObjectFromParameter(ParametersBase parameter) throws IllegalAccessException
    {
        // 获取URL
        StringBuilder urlBuilder = new StringBuilder();

        urlBuilder.append(parameter.subURL().contains("%COMPLETE_URL%") ? parameter.subURL().replace("%COMPLETE_URL%", "") + "?" : BASE_URL + parameter.subURL() + "?");

        for (Field field : parameter.getClass().getDeclaredFields())
        {
            if (field.isAnnotationPresent(HttpParameter.class))
            {
                boolean required = field.getAnnotation(HttpParameter.class).required();

                String value = ReflectUtils.getValue(field, urlBuilder).toString();

                if (value != null)
                {
                    // 添加参数
                    StringUtils.addParameter(urlBuilder, field.getName(), value);
                }
                else if (required)
                {
                    // TODO: 必要参数值是null的情况
                    return null;
                }
            }
        }

        System.out.println("获取到的URL请求: " + urlBuilder.toString());

        return DownloadUtils.getJSONObjectFromURL(urlBuilder.toString());
    }
}
