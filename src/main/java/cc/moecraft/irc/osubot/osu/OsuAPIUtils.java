package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.JsonUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.irc.osubot.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
     * 判断一个用户名在Osu官方数据库里是否存在
     * @param username 用户名
     * @return 是否存在
     */
    public boolean isUserExisting(String username) throws IllegalAccessException
    {
        try {
            JsonArray array = getJsonElementFromParameter(UserParameters.builder().u(username).build()).getAsJsonArray();
            return !(array.size() == 0);
        } catch (MalformedURLException | RequiredParamIsNullException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 用HTTP参数直接获取数据类对象
     * @param parameter HTTP参数
     * @return 数据类对象
     * @throws IllegalAccessException 没有权限访问反射到的变量
     */
    public ArrayList<DataBase> get(ParametersBase parameter) throws IllegalAccessException, JsonEmptyException, MalformedURLException, RequiredParamIsNullException
    {
        JsonElement jsonElement = getJsonElementFromParameter(parameter);

        if (jsonElement.isJsonNull()) throw new JsonEmptyException();

        JsonArray jsonArray;

        // 在Osu官方API获取的话可能获取到Array
        if (jsonElement.isJsonArray()) jsonArray = jsonElement.getAsJsonArray();
        else if (jsonElement.isJsonObject()) jsonArray = JsonUtils.toJsonArray(jsonElement);
        else throw new JsonEmptyException(); // 既不是JsonArray也不是JsonObject的情况

        ArrayList<DataBase> data = new ArrayList<>();

        // 赋值
        for (JsonElement element : jsonArray)
        {
            data.add(new Gson().fromJson(element, (Type) parameter.dataStorageClass()));
        }

        return data;
    }

    /**
     * 用HTTP参数获取JSONObject
     *
     * @param parameter HTTP参数
     * @return JSON对象
     * @throws IllegalAccessException 反射失败
     */
    public JsonElement getJsonElementFromParameter(ParametersBase parameter) throws IllegalAccessException, MalformedURLException, RequiredParamIsNullException
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
                    throw new RequiredParamIsNullException();
                }
            }
        }

        Main.getLogger().debug("正在获取... URL: " + urlBuilder.toString());

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
