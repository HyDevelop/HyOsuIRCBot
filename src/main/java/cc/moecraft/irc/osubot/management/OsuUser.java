package cc.moecraft.irc.osubot.management;

import cc.moecraft.irc.osubot.Main;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class OsuUser extends Permissible
{
    private int osuId;

    private String username;

    // TODO: 我觉得这里除了名字或者OsuID以外所有变量都应该像isAdmin和setAdmin那样动态获取和设置
    // TODO: 这样的话就可以直接用名字或者OsuID获取User了.
    // TODO: 如果用名字就动态获取ID, 如果用ID就动态获取名字.

    public OsuUser(String username)
    {
        this.username = username;

        setGroups(Main.getPermissionConfig().getUserPermissionGroups(username));
    }

    public int getOsuId()
    {
        return osuId;
    }

    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAdmin()
    {
        return Main.getConfig().getAdminUsernames().contains(username);
    }

    @Override
    public void setAdmin(boolean admin)
    {
        if (admin) Main.getConfig().getAdminUsernames().add(username);
        else Main.getConfig().getAdminUsernames().remove(username);

        Main.getConfig().save();
    }
}
