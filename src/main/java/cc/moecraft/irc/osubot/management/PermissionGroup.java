package cc.moecraft.irc.osubot.management;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class PermissionGroup
{
    private String groupName; // 权限组名字
    private ArrayList<PermissionGroup> containings; // 包含的其他权限组
    private ArrayList<Permission> permissions; // 包含的权限

    public PermissionGroup(String groupName)
    {
        this.groupName = groupName;

        containings = new ArrayList<>();
        permissions = new ArrayList<>();
    }

    /**
     * 判断是否有权限
     * @param permission 权限
     * @return 是否有权限
     */
    public boolean hasPermission(String permission)
    {
        for (Permission eachPermission : getAllPermissions())
            if (eachPermission.hasPermission(permission)) return true;
        return false;
    }

    /**
     * 获取用户组包含的所有的权限
     * @return 所有的权限
     */
    public ArrayList<Permission> getAllPermissions()
    {
        ArrayList<Permission> result = new ArrayList<>(permissions);

        containings.forEach(containing -> result.addAll(containing.getAllPermissions()));

        return result;
    }

    /**
     * 获取当前用户组的权限
     * @return 当前用户组的权限
     */
    public ArrayList<Permission> getThisGroupPermissions()
    {
        return permissions;
    }

    public PermissionGroup setPermissions(ArrayList<Permission> permissions)
    {
        this.permissions = permissions;
        return this;
    }

    public ArrayList<PermissionGroup> getContainings()
    {
        return containings;
    }

    public PermissionGroup setContainings(ArrayList<PermissionGroup> containings)
    {
        this.containings = containings;
        return this;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
}
