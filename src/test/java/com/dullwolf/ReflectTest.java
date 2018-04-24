package com.dullwolf;

import cc.moecraft.irc.osubot.DebugLogger;
import cc.moecraft.irc.osubot.osu.data.UserData;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class ReflectTest
{
    private static DebugLogger logger = new DebugLogger("Test", true);

    public static void main(String[] args)
    {
        UserData userData = new UserData();

        Field[] fields = UserData.class.getDeclaredFields();

        StringBuilder fieldNames = new StringBuilder("[");

        for (Field field : fields)
        {
            fieldNames.append(field.getName()).append(", ");
        }

        logger.debug("Fields:" + fieldNames.append("]").toString());
        logger.debug("- length = " + fields.length);
    }
}
