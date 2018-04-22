package cc.moecraft.irc.osubot.management;

import cc.moecraft.irc.osubot.Main;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class User extends Permissible
{
    private int osuId;

    private String username;

    public int getOsuId()
    {
        return osuId;
    }

    public User setOsuId(int osuId)
    {
        this.osuId = osuId;
        return this;
    }

    public String getUsername()
    {
        return username;
    }

    public User setUsername(String username)
    {
        this.username = username;
        return this;
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
