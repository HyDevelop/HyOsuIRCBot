package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.Messenger;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import cc.moecraft.irc.osubot.utils.ArrayUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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
     *  !s [用户名]     查询其他玩家信息
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
        String username;

        if (args.size() == 0) username = sender.getNick();
        else username = ArrayUtils.getTheRestArgsAsString(args, 0);

        try
        {
            if (!Main.getOsuAPIUtils().isUserExisting(username))
            {
                Main.getMessenger().respond(event, "未知用户: " + username);
                return;
            }

            UserData userData = (UserData) Main.getOsuAPIUtils().get(UserParameters.builder().u(username).build()).get(0);

            Main.getMessenger().respond(event, ReflectUtils.replaceReflectVariables(userData, "[%username%(%user_id%)]: %pp_raw%pp | lv.%level% | %accuracy%acc. | %count_rank_ssh%ssh | %count_rank_ss%ss |  %count_rank_sh%sh |  %count_rank_s%s |  %count_rank_a%a "));
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
        return "irc.user.regular.osu.stats";
    }
}
