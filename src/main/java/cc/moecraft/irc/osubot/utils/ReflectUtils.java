package cc.moecraft.irc.osubot.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
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
     * 获取Getter
     * @param field 变量名
     * @param object 对象
     * @return Getter方法
     */
    public static Method getGetter(Field field, Object object)
    {
        return getGetterOrSetter(field, object, "get");
    }

    /**
     * 获取Setter
     * @param field 变量名
     * @param object 对象
     * @return Setter方法
     */
    public static Method getSetter(Field field, Object object)
    {
        return getGetterOrSetter(field, object, "set");
    }

    /**
     * 获取一个对象类里面定义的Get或者Set方法
     * @param field 变量名
     * @param object 对象
     * @param getOrSet 如果是"get"获取的就是Getter, 如果是"set"获取的就是Setter
     * @return Get方法或者Set方法
     */
    public static Method getGetterOrSetter(Field field, Object object, String getOrSet)
    {
        for (Method method : object.getClass().getMethods())
        {
            if (method.getName().startsWith(getOrSet))
            {
                String methodName = method.getName().toLowerCase();

                methodName = methodName.replaceFirst(getOrSet, "");

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