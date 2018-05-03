package cc.moecraft.irc.osubot.utils;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
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
     * 判断一个类是不是数字类
     *
     * @param targetClass 目标类
     * @return 是不是数字类
     */
    public static boolean isNumeric(Class targetClass)
    {
        ArrayList<Class> listThatIsConsideredNumeric = new ArrayList<>();
        listThatIsConsideredNumeric.add(Byte.class);
        listThatIsConsideredNumeric.add(Short.class);
        listThatIsConsideredNumeric.add(Integer.class);
        listThatIsConsideredNumeric.add(Long.class);
        listThatIsConsideredNumeric.add(Float.class);
        listThatIsConsideredNumeric.add(Double.class);
        listThatIsConsideredNumeric.add(byte.class);
        listThatIsConsideredNumeric.add(short.class);
        listThatIsConsideredNumeric.add(int.class);
        listThatIsConsideredNumeric.add(long.class);
        listThatIsConsideredNumeric.add(float.class);
        listThatIsConsideredNumeric.add(double.class);

        return listThatIsConsideredNumeric.contains(targetClass);
    }

    /**
     * 判断一个类是不是能有小数的类
     *
     * @param targetClass 目标类
     * @return 是不是能有小数的类
     */
    public static boolean isDecimal(Class targetClass)
    {
        ArrayList<Class> listThatIsConsideredDecimal = new ArrayList<>();
        listThatIsConsideredDecimal.add(Float.class);
        listThatIsConsideredDecimal.add(Double.class);
        listThatIsConsideredDecimal.add(float.class);
        listThatIsConsideredDecimal.add(double.class);

        return listThatIsConsideredDecimal.contains(targetClass);
    }

    /**
     * 判断一个类是不是Double
     *
     * @param targetClass 目标类
     * @return 是不是Double
     */
    public static boolean isDouble(Class targetClass)
    {
        return targetClass == Double.class || targetClass == double.class;
    }

    /**
     * 判断一个类是不是Float
     *
     * @param targetClass 目标类
     * @return 是不是Float
     */
    public static boolean isFloat(Class targetClass)
    {
        return targetClass == Float.class || targetClass == float.class;
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
     * @param positiveSigns 要不要显示+号
     * @return 替换后的字符串
     */
    public static String replaceReflectVariables(Object object, String format, boolean positiveSigns, boolean useGsonNames)
    {
        for (Field field : object.getClass().getDeclaredFields())
        {
            String fieldName;

            if (useGsonNames)
            {
                if (field.isAnnotationPresent(SerializedName.class))
                    fieldName = field.getAnnotation(SerializedName.class).value();
                else continue;
            }
            else fieldName = field.getName();

            String variableName = String.format("%%%s%%", fieldName);

            field.setAccessible(true);

            try
            {
                String value = field.get(object).toString();

                if (positiveSigns)
                {
                    try {
                        double numericValue = Double.parseDouble(value);
                        if (numericValue >= 0D) value = "+" + value;
                    } catch (Exception ignored) { } // 不是数字
                }

                format = format.replace(variableName, value);
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }

        return format;
    }

    /**
     * 反射四舍五入所有数值
     *
     * @param object 对象
     * @param decimals 小数点后几位 ( 0: 1 | 1: 0.1 | 4: 0.0001 )
     */
    public static void roundAllNumbers(Object object, int decimals) throws IllegalAccessException
    {
        for (Field field : object.getClass().getDeclaredFields())
        {
            field.setAccessible(true);

            if (isFloat(field.getType()))
            {
                field.set(object, Math.round((float) field.get(object) * (float) (Math.pow(10d, decimals))) / (float) (Math.pow(10d, decimals)));
            }
            if (isDouble(field.getType()))
            {
                field.set(object, Math.round((double) field.get(object) * Math.pow(10d, decimals)) / Math.pow(10d, decimals));
            }
        }
    }

    /**
     * 赋值助手 ( 考虑到可能需要递归就分开写了 )
     *
     * @param parameter HTTP参数
     * @param dataBaseList 数据类存储对象列表
     * @param jsonArray Json对象列表
     * @throws IllegalAccessException 没有权限访问反射到的变量
     * @throws InstantiationException 反射创建实例失败
     * @throws InvocationTargetException 反射方法激活失败
     */
    @Deprecated
    public static void assignHelper(ParametersBase parameter, ArrayList<DataBase> dataBaseList, JsonArray jsonArray) throws IllegalAccessException, InstantiationException, InvocationTargetException
    {
        for (int i = 0; i < jsonArray.size(); i++)
        {
            JsonObject element = jsonArray.get(i).getAsJsonObject(); // TODO: 如果两个Array套在一起就会出错

            // 反射添加新的实例
            dataBaseList.add((DataBase) parameter.dataStorageClass().newInstance());
            DataBase data = dataBaseList.get(i);

            // 反射赋值
            assignHelper2(data, element);
        }
    }

    /**
     * 赋值递归助手 ( 考虑到可能需要递归就分开写了 )
     *
     * @param object 赋值对象
     * @param element JSON对象
     * @throws IllegalAccessException 没有权限访问反射到的变量
     * @throws InvocationTargetException 反射方法激活失败
     */
    @Deprecated
    public static void assignHelper2(Object object, JsonObject element) throws InvocationTargetException, IllegalAccessException
    {
        for (Field field : object.getClass().getDeclaredFields())
        {
            if (element.keySet().contains(field.getName()))
            {
                // 赋值 ( 类型转换可能出错
                field.setAccessible(true);

                // 如果是基础类, 反射获取getAs方法转换
                if (ReflectUtils.isPrimitive(field.getType()))
                {
                    JsonPrimitive primitive = element.get(field.getName()).getAsJsonPrimitive();

                    field.set(object, Objects.requireNonNull(ReflectUtils.getJsonPrimitiveGetAsMethod(field, primitive)).invoke(primitive));
                }
                else
                {
                    // 不是基础类, 递归
                    System.out.println("递归: " + field.getType().getSimpleName());
                    assignHelper2(field.get(object), (JsonObject) element.get(field.getName()));
                }
            }
        }
    }
}
