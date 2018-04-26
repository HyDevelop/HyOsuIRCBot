package com.dullwolf;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class ReflectTest
{
    private static DebugLogger logger = new DebugLogger("Test", true);

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException
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

        Field field = fields[new Random().nextInt(fields.length - 1)];

        logger.debug("用来测试获取Getter的Field: " + field);

        Method getter = ReflectUtils.getGetter(field, userData);

        assert getter != null;
        logger.debug("获取到的Getter: " + getter.getName());

        logger.debug("运行Getter获取到的数据: " + getter.invoke(userData));
    }
}
