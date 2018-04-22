package cc.moecraft.irc.osubot.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class Permissible
{
    private boolean admin; // 管理员无视任何权限检测
    private ArrayList<PermissionGroup> groups;

    /**
     * 判断是否有权限
     * @param permission 权限
     * @return 是否有权限
     */
    public boolean hasPermission(String permission)
    {
        if (admin) return true;

        for (Permission eachPermission : getAllPermissions())
            if (eachPermission.hasPermission(permission)) return true;
        return false;
    }

    /**
     * 获取用户所有权限
     * @return 所有权限
     */
    public ArrayList<Permission> getAllPermissions()
    {
        ArrayList<Permission> result = new ArrayList<>();

        groups.forEach(group -> result.addAll(group.getAllPermissions()));

        return result;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    public void setAdmin(boolean admin)
    {
        this.admin = admin; //TODO: 配置文件
    }

    public ArrayList<PermissionGroup> getGroups()
    {
        return groups;
    }

    public void setGroups(ArrayList<PermissionGroup> groups)
    {
        this.groups = groups;
    }
}
