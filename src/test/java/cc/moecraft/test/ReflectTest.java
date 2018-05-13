package cc.moecraft.test;

import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.parameters.UserBestParameters;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.logger.DebugLogger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class ReflectTest
{
    private static DebugLogger logger = new DebugLogger("Test", true);

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException
    {
        UserBestParameters object = (UserBestParameters) UserBestParameters.builder().u("hykilpikonna").m("0").build();

        Field[] fields = UserData.class.getDeclaredFields();

        StringBuilder fieldNames = new StringBuilder("[");
        StringBuilder fieldDatas = new StringBuilder("[");

        for (Field field : fields)
        {
            fieldNames.append(field.getName()).append(", ");

            fieldDatas.append(ReflectUtils.getValue(field, object)).append(", ");
        }

        logger.debug("Field Names: " + fieldNames.append("]").toString());
        logger.debug("Field Datas: " + fieldDatas.append("]").toString());
    }
}
