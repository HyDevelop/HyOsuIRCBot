package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.parameters.UserParameters;
import cc.moecraft.irc.osubot.utils.ArrayUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
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
        UsernameAndMode usernameAndMode = getUsernameAndModeWithArgs(sender, args);

        try
        {
            if (!Main.getOsuAPIUtils().isUserExisting(usernameAndMode.getUsername()))
            {
                Main.getMessenger().respond(event, "未知用户: " + usernameAndMode.getUsername());
                return;
            }

            UserData userData = (UserData) Main.getOsuAPIUtils().get(UserParameters.builder().m(("" + usernameAndMode.mode)).u(usernameAndMode.getUsername()).build()).get(0);

            // 四舍五入
            ReflectUtils.roundAllNumbers(userData, 1);

            // SS和SSH加起来, S和SH加起来
            userData.setCount_rank_s(userData.getCount_rank_s() + userData.getCount_rank_sh());
            userData.setCount_rank_ss(userData.getCount_rank_ss() + userData.getCount_rank_ssh());

            Main.getMessenger().respond(event, ReflectUtils.replaceReflectVariables(userData, "[%username% (%user_id%)]: %pp_raw%pp | lv.%level% | %accuracy%% acc. | %count_rank_ss%ss | %count_rank_s%s |  %count_rank_a%a "));
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

    /**
     * 获取玩家和模式
     * @param sender 发送者
     * @param args 指令
     * @return 玩家和模式
     */
    public UsernameAndMode getUsernameAndModeWithArgs(User sender, ArrayList<String> args)
    {
        String username;
        int mode = 0;

        if (args.size() == 0) username = sender.getNick();
        else
        {
            int modeTemp = OsuAPIUtils.getModeWithName(args.get(0));

            if (modeTemp == -1) username = ArrayUtils.getTheRestArgsAsString(args, 0);
            else
            {
                mode = modeTemp;
                if (args.size() == 1) username = sender.getNick();
                else username = ArrayUtils.getTheRestArgsAsString(args, 1);
            }
        }

        return new UsernameAndMode(mode, username);
    }

    @Data
    @AllArgsConstructor
    public class UsernameAndMode
    {
        private int mode;
        private String username;
    }
}
