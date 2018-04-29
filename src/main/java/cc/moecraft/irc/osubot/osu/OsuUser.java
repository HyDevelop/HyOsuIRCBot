package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.management.Permissible;
import cc.moecraft.irc.osubot.osu.data.OsuTrackData;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.parameters.OsuTrackParameters;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import lombok.*;

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
            return getData(new UsernameAndMode(0, username));
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
     * @param usernameAndMode 用户名和模式
     * @return 玩家数据
     */
    public static UserData getData(UsernameAndMode usernameAndMode) throws IllegalAccessException, InvocationTargetException, InstantiationException
    {
        return (UserData) Main.getOsuAPIUtils().get(UserParameters.builder().u(usernameAndMode.getUsername()).type("string").m("" + usernameAndMode.getMode()).build()).get(0);
    }

    /**
     * 获取OsuTrack玩家数据
     *
     * @param usernameAndMode 用户名和模式
     * @return 玩家数据
     */
    public static OsuTrackData getOsuTrackData(UsernameAndMode usernameAndMode) throws IllegalAccessException, InvocationTargetException, InstantiationException
    {
        return (OsuTrackData) Main.getOsuAPIUtils().get(OsuTrackParameters.builder().user(usernameAndMode.getUsername()).mode("" + usernameAndMode.getMode()).build()).get(0);
    }

    /**
     * 封装: 获取OsuTrack链接
     *
     * @param usernameAndMode 用户名和模式
     * @return 链接
     */
    public static String getOsuTrackLink(UsernameAndMode usernameAndMode)
    {
        return getOsuTrackLink(usernameAndMode.getUsername());
    }

    /**
     * 获取OsuTrack连接
     *
     * @param username 用户名
     * @return 链接
     */
    public static String getOsuTrackLink(String username)
    {
        return String.format("https://ameobea.me/osutrack/user/%s/", username);
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

    @Data
    @RequiredArgsConstructor
    public static class UsernameAndMode
    {
        @NonNull
        private int mode;
        @NonNull
        private String username;
        private boolean self = false;
    }

    /**
     * 存放数据的位置 ( 
     * OSU:        Osu服务器上最新的数据
     * TODO: DATABASE:   数据库里的数据, 不是最新
     * AMEO_TRACK: Ameo写的Osu!Track数据统计的数据
     */
    public enum StorageLocation
    {
        OSU,
        // DATABASE,
        AMEO_TRACK
    }
}