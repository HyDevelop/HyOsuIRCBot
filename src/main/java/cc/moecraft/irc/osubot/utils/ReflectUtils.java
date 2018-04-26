package cc.moecraft.irc.osubot.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class ReflectUtils
{
    /**
     * 反射获取变量值
     * @param field Field
     * @param object 变量
     * @return 值
     */
    public static Object getValue(Field field, Object object) throws IllegalAccessException
    {
        field.setAccessible(true);
        return field.get(object);
    }

    /**
     * 获取一个对象类里面定义的Get方法
     * @param field 变量名
     * @param object 对象
     * @return Get方法
     */
    public static Method getGetter(Field field, Object object)
    {
        for (Method method : object.getClass().getMethods())
        {
            if (method.getName().startsWith("get"))
            {
                String methodName = method.getName().toLowerCase();

                methodName = methodName.replaceFirst("get", "");

                if (methodName.equals(field.getName().toLowerCase())) return method;
            }
        }
        return null;
    }

    /**
     * 输出对象所有值
     * @param object 对象
     */
    public static void printAllValue(Object object) throws IllegalAccessException
    {
        System.out.println("正在输出此对象所有值...");

        for (Field field : object.getClass().getDeclaredFields())
        {
            System.out.println(String.format("- %s = %s", field.getName(), getValue(field, object)));
        }

        System.out.println("输出完成!");
    }
}
