package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.management.Permissible;
import cc.moecraft.irc.osubot.osu.data.OsuTrackData;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.OsuTrackParameters;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
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
    public UserData getData() throws JsonEmptyException, RequiredParamIsNullException, MalformedURLException, IllegalAccessException
    {
        return getData(new UsernameAndMode(0, username));
    }

    /**
     * 获取玩家数据
     *
     * @param usernameAndMode 用户名和模式
     * @return 玩家数据
     */
    public static UserData getData(UsernameAndMode usernameAndMode) throws IllegalAccessException, RequiredParamIsNullException, MalformedURLException, JsonEmptyException
    {
        return (UserData) Main.getOsuAPIUtils().get(UserParameters.builder().u(usernameAndMode.getUsername()).type("string").m("" + usernameAndMode.getMode()).build()).get(0);
    }

    /**
     * 获取玩家数据
     *
     * @param id 用户id
     * @return 玩家数据
     */
    public static UserData getData(int id, int mode) throws IllegalAccessException, RequiredParamIsNullException, MalformedURLException, JsonEmptyException
    {
        return (UserData) Main.getOsuAPIUtils().get(UserParameters.builder().u(String.valueOf(id)).type("id").m(String.valueOf(mode)).build()).get(0);
    }

    /**
     * 获取OsuTrack玩家数据
     *
     * @param usernameAndMode 用户名和模式
     * @return 玩家数据
     */
    public static OsuTrackData getOsuTrackData(UsernameAndMode usernameAndMode) throws IllegalAccessException, RequiredParamIsNullException, MalformedURLException, JsonEmptyException
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
    public static class UsernameAndMode
    {
        public UsernameAndMode(int mode, String username)
        {
            this.mode = mode; this.username = username;
        }

        private int mode;
        private String username;
        private boolean self = false;
    }
}
