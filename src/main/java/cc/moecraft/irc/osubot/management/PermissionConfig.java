package cc.moecraft.irc.osubot.management;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.yaml.Config;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class PermissionConfig extends Config
{
    private Map<String, PermissionGroup> loadedGroups;
    private PermissionGroup defaultGroup;

    public static final String GROUPS_PREFIX = "Groups.";
    public static final String USERS_PREFIX = "Users.";

    public PermissionConfig()
    {
        super(Main.VERSION, Main.PATH, "Permissions", "yml", false, false, true);
    }

    /**
     * 向配置写入一个权限组
     * @param group 权限组
     */
    public void setGroup(PermissionGroup group)
    {
        String currentPrefix = GROUPS_PREFIX + group.getGroupName() + ".";

        set(currentPrefix + "ContainingGroups", groupListToNameList(group.getContainings()));
        set(currentPrefix + "Permissions", group.getThisGroupPermissions());

        save();
    }

    /**
     * 从配置获取一个权限组
     * @param name 权限组名
     * @return 权限组
     */
    public PermissionGroup getGroup(String name)
    {
        if (loadedGroups.containsKey(name)) return loadedGroups.get(name);

        String currentPrefix = GROUPS_PREFIX + name + ".";

        PermissionGroup result = new PermissionGroup(name);

        ArrayList<String> containingGroupNames = (ArrayList<String>) getStringList(currentPrefix + "ContainingGroups");
        containingGroupNames.forEach(groupName -> result.getContainings().add(getGroup(groupName))); // 递归, 配置不好可能出现无限递归

        ArrayList<String> permissionNames = (ArrayList<String>) getStringList(currentPrefix + "Permissions");
        permissionNames.forEach(permissionName -> result.getThisGroupPermissions().add(new Permission(permissionName)));

        return result;
    }

    /**
     * 权限组列表转换到权限组名字列表
     * @param groups 权限组列表
     * @return 权限组名字列表
     */
    private ArrayList<String> groupListToNameList(ArrayList<PermissionGroup> groups)
    {
        ArrayList<String> result = new ArrayList<>();

        groups.forEach(permissionGroup -> result.add(permissionGroup.getGroupName()));

        return result;
    }

    /**
     * 获取用户权限组
     * @param username 用户
     * @return 权限组
     */
    public ArrayList<PermissionGroup> getUserPermissionGroups(String username) // TODO: 换成用Osu的UserID
    {
        String currentPrefix = USERS_PREFIX + username;

        ArrayList<String> permissionGroupNames = (ArrayList<String>) getStringList(currentPrefix);

        ArrayList<PermissionGroup> result = new ArrayList<>();

        permissionGroupNames.forEach(name -> result.add(getGroup(name)));

        return result;
    }

    /**
     * 保存用户权限组
     * @param user 用户
     */
    public void setUserPermissionGroups(User user) // TODO: 换成用Osu的UserID
    {
        String currentPrefix = USERS_PREFIX + user.getUsername();

        set(currentPrefix, groupListToNameList(user.getGroups()));

        save();
    }

    /**
     * 预加载, 可以重载
     */
    @Override
    public void readConfig()
    {
        loadedGroups = new HashMap<>();

        getKeys(GROUPS_PREFIX.replace(".", "")).forEach(key -> loadedGroups.put(key, getGroup(key)));

        defaultGroup = getGroup(getString("DefaultGroup"));
    }

    @Override
    public void writeConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        //TODO: 默认权限组
    }

    public PermissionGroup getDefaultGroup()
    {
        return defaultGroup;
    }

    public void setDefaultGroup(PermissionGroup defaultGroup)
    {
        this.defaultGroup = defaultGroup;

        set("DefaultGroup", defaultGroup.getGroupName());

        save();
    }
}
