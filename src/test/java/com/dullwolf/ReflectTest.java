package com.dullwolf;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class ReflectTest
{
    private static DebugLogger logger = new DebugLogger("Test", true);

    public static void main(String[] args) throws IllegalAccessException
    {
        UserData userData = new UserData(1,"test",1,1,1,1,1,1,1,1.5d,1.7d,1.8d,1,1,1,1,1,"cn",1);

        Field[] fields = UserData.class.getDeclaredFields();

        StringBuilder fieldNames = new StringBuilder("[");
        StringBuilder fieldDatas = new StringBuilder("[");

        for (Field field : fields)
        {
            fieldNames.append(field.getName()).append(", ");

            fieldDatas.append(ReflectUtils.getValue(field, userData)).append(", ");
        }

        logger.debug("Field Names: " + fieldNames.append("]").toString());
        logger.debug("Field Datas: " + fieldDatas.append("]").toString());
    }
}
