package cc.moecraft.irc.osubot.management;

import cc.moecraft.irc.osubot.Main;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public abstract class Permissible
{
    private ArrayList<PermissionGroup> groups;

    /**
     * 判断是否有权限
     * @param permission 权限
     * @return 是否有权限
     */
    public boolean hasPermission(String permission)
    {
        if (isAdmin()) return true;

        // Main.getLogger().debug("Main.getDefault: " + PermissionConfig.permissionListToNameList(Main.getPermissionConfig().getDefaultGroup().getAllPermissions()));
        if (Main.getPermissionConfig().getDefaultGroup().hasPermission(permission)) return true;

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

    public abstract boolean isAdmin();

    public abstract void setAdmin(boolean admin);

    public ArrayList<PermissionGroup> getGroups()
    {
        return groups;
    }

    public void setGroups(ArrayList<PermissionGroup> groups)
    {
        this.groups = groups;
    }
}
