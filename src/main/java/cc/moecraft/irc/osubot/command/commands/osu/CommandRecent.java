package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuAPIWrapper;
import cc.moecraft.irc.osubot.osu.OsuUser;
import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.data.UserRecentData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RecentScoreNotEnough;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.BeatmapParameters;
import cc.moecraft.irc.osubot.osu.parameters.UserRecentParameters;
import cc.moecraft.irc.osubot.utils.ArrayUtils;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.irc.osubot.utils.TimeUnits;
import cc.moecraft.irc.osubot.utils.TimeUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static cc.moecraft.irc.osubot.utils.ArrayUtils.getUsernameAndModeWithArgs;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class CommandRecent extends Command
{
    public CommandRecent()
    {
        super("recent", "r");
    }

    /**
     * 用法:
     *  !r                 查询自己最后一个成绩
     *  !r [定位]          查询自己某个成绩 ( 比如定位是2的话就是两局之前的成绩 )
     *  !r [t/m/c]         查询自己的某个模式最后一个成绩
     *  !r [t/m/c] [定位]  查询自己某个模式某个成绩 ( 比如定位是2的话就是两局之前的成绩 )
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
        UsernameAndIndexAndMode info = getIndexAndModeWithArgs(sender, args);

        if (info.getIndex() > 50)
        {
            Main.getMessenger().respond(event, "请输入50以下的数字");
            return;
        }

        try {
            UserRecentData data = Main.getWrapper().getRecent(info);

            BeatmapData beatmapData = Main.getWrapper().getBeatmap(data);

            // 获取Mode名字
            String modeName = OsuAPIUtils.getModeNameWithMode(beatmapData.getMode());

            // 四舍五入
            ReflectUtils.roundAllNumbers(data, 1);
            ReflectUtils.roundAllNumbers(beatmapData, 1);

            // 300 和 300p 加起来, 100 和 100p 加起来
            data.setCount100(data.getCount100() + data.getCount100p());
            data.setCount300(data.getCount300() + data.getCount300p());

            // TODO: PP显示, Mods显示
            String format = "[osu://b/%beatmap_id% [%cm%: %title% - %artist% (%version%)]]: ★ %difficultyrating% | 成绩: %rank% | PP计算还没有! | %score% | %maxcombo%x/%max_combo%x | %count300% %count100% %count50% %countmiss%";

            Main.getMessenger().respond(event,
                    ReflectUtils.replaceReflectVariables(data,
                            ReflectUtils.replaceReflectVariables(beatmapData,
                                    format,
                            false, true),
                    false, true
            ).replace("%cm%", modeName));

        } catch (IllegalAccessException | RequiredParamIsNullException | MalformedURLException e) {
            Main.getMessenger().respond(event, "未知后台错误, 请联系me@hydev.org");
            e.printStackTrace();
        } catch (JsonEmptyException e) {
            Main.getMessenger().respond(event, "未找到用户: " + info.getUsername() + ", 如果确定该用户存在, 请联系me@hydev.org");
            // TODO: 报错收集系统
            e.printStackTrace();
        } catch (RecentScoreNotEnough recentScoreNotEnough) {
            Main.getMessenger().respond(event, String.format("现在你%s模式的近期成绩只有%s个... 无法获取第%s个, 多玩玩再来看看吧!", OsuAPIUtils.getModeNameWithMode(recentScoreNotEnough.getMode()), recentScoreNotEnough.getLimit(), recentScoreNotEnough.getRequested()));
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.map";
    }

    /**
     * 获取玩家和模式
     * @param sender 发送者
     * @param args 指令
     * @return 玩家和模式
     */
    public static UsernameAndIndexAndMode getIndexAndModeWithArgs(User sender, ArrayList<String> args)
    {
        UsernameAndIndexAndMode result = new UsernameAndIndexAndMode(1, 0, "");
        result.setUsername(sender.getNick());
        result.setSelf(true);

        if (args.size() != 0)
        {
            int modeTemp = OsuAPIUtils.getModeWithName(args.get(0));

            if (modeTemp == -1)
            {
                result.setIndex(Integer.parseInt(args.get(0)));
            }
            else
            {
                result.setMode(modeTemp);
                if (args.size() != 1)
                {
                    result.setIndex(Integer.parseInt(args.get(1)));
                }
            }
        }

        return result;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UsernameAndIndexAndMode extends OsuUser.UsernameAndMode
    {
        private int index;

        UsernameAndIndexAndMode(int index, int mode, String username)
        {
            super(mode, username);
            this.index = index;
        }
    }
}
