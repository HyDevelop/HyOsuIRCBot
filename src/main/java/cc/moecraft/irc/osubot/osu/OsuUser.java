package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.management.Permissible;
import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class OsuUser extends Permissible
{
    @Getter
    private String username;

    // 这里本地存储的数据只有Username
    // 其他的变量全都动态从Osu服务器上或者数据库里获取.

    public OsuUser(String username)
    {
        this.username = username;
        setGroups(Main.getPermissionConfig().getUserPermissionGroups(username));
    }

    /**
     * 获取玩家数据
     * TODO: 默认模式
     *
     * @return 玩家数据
     */
    public UserData getData()
    {
        try
        {
            return (UserData) Main.getOsuAPIUtils().get(UserParameters.builder().u(username).type("string").build()).get(0);
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取玩家数据
     *
     * @param mode 模式
     * @return 玩家数据
     */
    public UserData getData(int mode)
    {
        try
        {
            return (UserData) Main.getOsuAPIUtils().get(UserParameters.builder().u(username).type("string").m("" + mode).build()).get(0);
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isAdmin()
    {
        return Main.getConfig().getAdminUsernames().contains(getUsername());
    }

    @Override
    public void setAdmin(boolean admin)
    {
        if (admin) Main.getConfig().getAdminUsernames().add(getUsername());
        else Main.getConfig().getAdminUsernames().remove(getUsername());

        Main.getConfig().save();
    }

    /* TODO: 实现这个
     * 存放数据的位置
     * OSU:        Osu服务器上最新的数据
     * DATABASE:   数据库里的数据, 不是最新
     * AMEO_TRACK: Ameo写的Osu!Track数据统计的数据
     *
    public enum StorageLocation
    {
        OSU, DATABASE, AMEO_TRACK
    }*/
}
