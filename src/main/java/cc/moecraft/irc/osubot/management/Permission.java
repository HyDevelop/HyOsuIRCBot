package cc.moecraft.irc.osubot.management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class Permission
{
    private String permission;

    /**
     * 权限构造器
     * @param permission 字符串形式的权限名 ( 比如 "irc.admin.*" )
     */
    public Permission(String permission)
    {
        this.permission = permission;
    }

    public boolean hasPermission(String detectingPermission)
    {
        if (permission.contains("*"))
        {
            return Pattern.compile(permission).matcher(detectingPermission).matches();
        }
        else
        {
            return permission.equals(detectingPermission);
        }
    }

    /**
     * 判断两条权限是否一样
     * @param obj 第二条权限
     * @return 是否一样
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Permission)
        {
            Permission permission = (Permission) obj;

            return permission.permission.equals(this.permission);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return permission;
    }
}
