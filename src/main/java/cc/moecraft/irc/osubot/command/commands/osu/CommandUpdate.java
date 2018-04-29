package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
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
 * QQ: admin@moecraft.cc -OR- 871674895
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

            // 新玩家 TODO： 检测数据库， 而不是OsuTrack服务器的数据库来判断是不是新玩家
            if (osuTrackData.isFirst() && usernameAndMode.isSelf())
            {
                Main.getMessenger().respond(event, "欢迎新大佬使用HyIRC机器人! 这个指令是Ameo的[https://ameobea.me/osutrack/ Osu!Track]统计功能!");
                Main.getMessenger().respond(event, "这个指令的数值代表着从上次输入指令到这次输入指令之间的进步!");
                return;
            }

            // 获取Mode名字
            String modeName = OsuAPIUtils.getModeNameWithMode(usernameAndMode.getMode());

            Main.getMessenger().respond(event, getPrefix(osuTrackData) + ReflectUtils.replaceReflectVariablesWithPositiveAndNegativeSigns(osuTrackData, "[%m% - [%link% %username%]]: %pp_raw% pp | %level% lvl | %pp_rank% rank | %accuracy%% acc. | %playcount% 次游戏 ").replace("%m%", modeName).replace("%link%", OsuUser.getOsuTrackLink(usernameAndMode)));
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            Main.getMessenger().respond(event, "未知后台错误, 请联系admin@moecraft.cc");
            // TODO: 报错收集系统 ( 不知道可不可能实现
            e.printStackTrace();
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.update";
    }

    /**
     * 获取适应玩家环境的前缀消息
     *
     * 例子：
     *  +0  +0  ： “多玩玩再来看吧!”
     *  +20 +400： “进步了.. 加油!”
     *  +99 +999： “w.. 大..大佬!”
     *  TODO: 随机消息列表
     *
     * @param osuTrackData 数据
     * @return 前缀消息
     */
    public String getPrefix(OsuTrackData osuTrackData)
    {
        if (osuTrackData.getPp_rank() <= 0 && osuTrackData.getPp_raw() <= 0)
        {
            return "多玩玩再来看吧! ";
        }
        else if (osuTrackData.getPp_rank() <= 400 && osuTrackData.getPp_raw() <= 20)
        {
            return "进步了.. 加油! ";
        }
        else if (osuTrackData.getPp_rank() > 400 && osuTrackData.getPp_raw() > 20)
        {
            return "w.. 大..大佬! ";
        }

        return "啊哈哈.... ";
    }
}
