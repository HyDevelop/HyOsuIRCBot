package cc.moecraft.irc.osubot.utils;

import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static java.lang.System.in;

/**
 * 此类由 Hykilpikonna 在 2018/04/25 创建!
 * Created by Hykilpikonna on 2018/04/25!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class InputUtils
{
    /**
     * 让用户输入所有HTTP参数 ( 测试的时候用
     * ( 其实写这个真的只是想练手...
     * @param parameters HTTP参数对象
     * @return 用户填满的参数对象
     */
    public static ParametersBase inputAllParams(ParametersBase parameters) throws IOException, InvocationTargetException, IllegalAccessException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        for (Field field : parameters.getClass().getDeclaredFields())
        {
            if (field.isAnnotationPresent(HttpParameter.class))
            {
                boolean required = field.getAnnotation(HttpParameter.class).required();

                if (required)
                {
                    System.out.println("输入 " + field.getName() + " : ");

                    ReflectUtils.getSetter(field, parameters).invoke(parameters, field.getType().cast(reader.readLine()));
                }
                else
                {
                    System.out.println("输入 " + field.getName() + " (不必要): ");

                    String input = reader.readLine();

                    if (input == null || input.isEmpty()) continue;

                    ReflectUtils.getSetter(field, parameters).invoke(parameters, field.getType().cast(input));
                }
            }
        }

        return parameters;
    }
}
