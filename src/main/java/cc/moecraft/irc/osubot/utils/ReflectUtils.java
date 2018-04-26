package cc.moecraft.irc.osubot.utils;

import com.google.gson.JsonPrimitive;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

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
     * 获取JsonPrimitive的"getAs***()"方法
     * @param field Field
     * @param jsonPrimitive Json原始对象
     * @return 获取到的方法
     */
    public static Method getJsonPrimitiveGetAsMethod(Field field, JsonPrimitive jsonPrimitive)
    {
        String fieldSimpleName = field.getType().getSimpleName();

        for (Method method : jsonPrimitive.getClass().getMethods())
        {
            if (method.getName().startsWith("getAs"))
            {
                String methodName = method.getName();

                methodName = methodName.replaceFirst("getAs", "");

                if (methodName.equalsIgnoreCase(fieldSimpleName)) return method;
            }
        }
        return null;
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

    /**
     * 查看一个类是不是Gson的JsonPrimitive里面可以存的类的变量
     * @param targetClass 目标类
     * @return 是不是Primitive类
     */
    public static boolean isPrimitive(Class targetClass)
    {
        ArrayList<Class> listThatIsConsideredPrimitive = new ArrayList<>();
        listThatIsConsideredPrimitive.add(Boolean.class);
        listThatIsConsideredPrimitive.add(String.class);
        listThatIsConsideredPrimitive.add(Character.class);
        listThatIsConsideredPrimitive.add(Byte.class);
        listThatIsConsideredPrimitive.add(Short.class);
        listThatIsConsideredPrimitive.add(Integer.class);
        listThatIsConsideredPrimitive.add(Long.class);
        listThatIsConsideredPrimitive.add(Float.class);
        listThatIsConsideredPrimitive.add(Double.class);

        return targetClass.isPrimitive() || listThatIsConsideredPrimitive.contains(targetClass);
    }

    /**
     * 反射替换变量
     *
     * 例子:
     *   输入:
     *   - object: [username = "Hykilpikonna", user_id = "666666", pp_raw = "99999999"] ( 别想了梦里什么都有
     *   - format = "[%username%(%user_id%)]: %pp_raw%"
     *   输出:
     *   "[Hykilpikonna(666666)]: 99999999"
     *
     *   练手 +1
     *
     * @param object 对象
     * @param format 格式
     * @return 替换后的字符串
     */
    public static String replaceReflectVariables(Object object, String format)
    {
        for (Field field : object.getClass().getDeclaredFields())
        {
            String variableName = String.format("%%%s%%", field.getName());

            field.setAccessible(true);

            try
            {
                format = format.replace(variableName, field.get(object).toString());
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }

        return format;
    }
}
