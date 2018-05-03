package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuUser;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static cc.moecraft.irc.osubot.utils.ArrayUtils.getUsernameAndModeWithArgs;

/**
 * 此类由 Hykilpikonna 在 2018/04/23 创建!
 * Created by Hykilpikonna on 2018/04/23!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class CommandStats extends Command
{
    public CommandStats()
    {
        super("stats", "s", "info");
    }

    /**
     * 查询玩家信息
     *  !s              查询自己的信息
     *  !s [用户名]      查询其他玩家信息
     *  !s [t/m/c]      查询自己的某个模式的信息
     *  !s [t/m/c] [名] 查询其他玩家某个模式的信息
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

            UserData userData = OsuUser.getData(usernameAndMode);

            // 四舍五入
            ReflectUtils.roundAllNumbers(userData, 1);

            // SS和SSH加起来, S和SH加起来
            userData.setCountRankS(userData.getCountRankS() + userData.getCountRankSh());
            userData.setCountRankSs(userData.getCountRankSs() + userData.getCountRankSsh());

            // 获取Mode名字
            String modeName = OsuAPIUtils.getModeNameWithMode(usernameAndMode.getMode());

            Main.getMessenger().respond(event, ReflectUtils.replaceReflectVariables(userData,
                    "[%mode% - %username% (%user_id%)]: %pp_raw%pp | lv.%level% | #%pp_rank% | %accuracy%% acc. | %count_rank_ss%ss | %count_rank_s%s |  %count_rank_a%a ",
                    false, true
            ).replace("%mode%", modeName));
        } catch (IllegalAccessException | RequiredParamIsNullException | MalformedURLException e) {
            Main.getMessenger().respond(event, "未知后台错误, 请联系admin@moecraft.cc");
            e.printStackTrace();
        } catch (JsonEmptyException e) {
            Main.getMessenger().respond(event, "未找到用户: " + usernameAndMode.getUsername() + ", 如果确定该用户存在, 请联系admin@moecraft.cc");
            // TODO: 报错收集系统
            e.printStackTrace();
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.stats";
    }
}
