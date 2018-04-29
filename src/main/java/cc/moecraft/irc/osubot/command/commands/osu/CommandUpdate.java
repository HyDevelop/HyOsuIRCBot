package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.osu.OsuUser;
import cc.moecraft.irc.osubot.osu.data.OsuTrackData;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static cc.moecraft.irc.osubot.utils.ArrayUtils.getUsernameAndModeWithArgs;

/**
 * 此类由 Hykilpikonna 在 2018/04/23 创建!
 * Created by Hykilpikonna on 2018/04/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandUpdate extends Command
{
    public CommandUpdate()
    {
        super("update", "u", "home");
    }

    /**
     * 更新玩家信息
     *  !u              更新自己的信息
     *  !u [用户名]      更新其他玩家信息
     *  !u [t/m/c]      更新自己的某个模式的信息
     *  !u [t/m/c] [名] 更新其他玩家某个模式的信息
     *
     * @param event 事件
     * @param sender 发送者的irc用户 ( 在osu的irc服务器的话用户名就是osu的用户名 )
     * @param channel 频道
     * @param command 指令名 ( 不包含指令参数 )
     * @param args 指令参数 ( 不包含指令名 )
     */
    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        OsuUser.UsernameAndMode usernameAndMode = getUsernameAndModeWithArgs(sender, args);

        try
        {
            if (!Main.getOsuAPIUtils().isUserExisting(usernameAndMode.getUsername()))
            {
                Main.getMessenger().respond(event, "未知用户: " + usernameAndMode.getUsername());
                return;
            }

            OsuTrackData osuTrackData = OsuUser.getOsuTrackData(usernameAndMode);

            // 四舍五入
            ReflectUtils.roundAllNumbers(osuTrackData, 1);

            Main.getMessenger().respond(event, ReflectUtils.replaceReflectVariables(osuTrackData, "[%mode% - %username%]: %pp_raw%pp | lv.%level% | %accuracy%% acc. | %count_rank_ss%ss | %count_rank_s%s |  %count_rank_a%a "));
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            Main.getMessenger().respond(event, "未知后台错误, 请联系me@hydev.org");
            // TODO: 报错收集系统 ( 不知道可不可能实现
            e.printStackTrace();
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.update";
    }

    /* TODO: 实现这个
    public String getPrefix(OsuTrackData osuTrackData)
    {

    }*/
}
