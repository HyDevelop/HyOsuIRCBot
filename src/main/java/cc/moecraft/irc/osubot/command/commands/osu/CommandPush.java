package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.ChannelCommand;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuUser;
import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.data.UserRecentData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RecentScoreNotEnoughException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.utils.*;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class CommandPush extends Command implements ChannelCommand
{
    public CommandPush()
    {
        super("push", "p");
    }

    /**
     * 用法:
     *  给其他人推荐谱面 (私聊输入):
     *
     *  !push [用户名]         推荐给某个玩家你刚刚玩的图
     *  !push [模式] [用户名]  推荐给某个玩家你刚刚玩某个模式的图
     *  .
     *  .
     *  给其他人推荐谱面 (在频道里输入):
     *
     *  !push                  推荐给大家你刚刚玩的图
     *  !push [模式]           推荐给大家你刚刚玩的其他模式的图
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
        int mode = ArgsUtils.getMode(args, 0);
        String username;

        if (mode == -1)
        {
            username = ArrayUtils.getTheRestArgsAsString(args, 0);
            mode = 0;
        }
        else
        {
            username = ArrayUtils.getTheRestArgsAsString(args, 1);
        }

        if (args.size() < 1)
        {
            Main.getMessenger().respond(event, "请输入用户名... 你不会真的想推荐给自己吧...");
            return;
        }

        process(event, sender, mode, username.replace(" ", "_"),
                "%username%推荐给你了刚刚在玩的谱面: [osu://b/%beatmap_id% [%cm%: %title% - %artist% (%version%)]]: ⏳ %ct% | ★ %difficultyrating% | BPM %bpm% | AR %diff_approach%");
    }

    @Override
    public void channel(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        int mode;
        if (args.size() == 0) mode = 0;
        else if (args.size() == 1) mode = ArgsUtils.getMode(args, 0);
        else return;

        if (mode == -1) return;
        process(event, sender, mode, channel.getName(),
                "%username%推荐给你们了刚刚在玩的谱面: [osu://b/%beatmap_id% [%cm%: %title% - %artist% (%version%)]]: ⏳ %ct% | ★ %difficultyrating% | BPM %bpm% | AR %diff_approach%");
    }

    public void process(GenericMessageEvent event, User sender, int mode, String placeToSend, String format)
    {
        CommandRecent.UsernameAndIndexAndMode info = new CommandRecent.UsernameAndIndexAndMode(1, mode, sender.getNick());

        try {
            UserRecentData data = Main.getWrapper().getRecent(info);

            BeatmapData beatmapData = Main.getWrapper().getBeatmap(data);

            UserData userData = OsuUser.getData(data.getUserId(), beatmapData.getMode());

            // 获取Mode名字
            String modeName = OsuAPIUtils.getModeNameWithMode(beatmapData.getMode());

            // 四舍五入
            ReflectUtils.roundAllNumbers(data, 1);
            ReflectUtils.roundAllNumbers(beatmapData, 1);

            // 获取时间
            String time = TimeUtils.convertToString("m:ss", beatmapData.getHitLength(), TimeUnits.Second);

            format = ReflectUtils.replaceReflectVariables(data, format, false, true);
            format = ReflectUtils.replaceReflectVariables(beatmapData, format, false, true);
            format = ReflectUtils.replaceReflectVariables(userData, format, false, true);
            format = format.replace("%cm%", modeName).replace("%ct%", time);

            event.getBot().sendIRC().message(placeToSend, format);

        } catch (IllegalAccessException | RequiredParamIsNullException | MalformedURLException e) {
            Main.getMessenger().respond(event, "未知后台错误, 请联系me@hydev.org");
            e.printStackTrace();
        } catch (JsonEmptyException e) {
            Main.getMessenger().respond(event, "未找到用户: " + info.getUsername() + ", 如果确定该用户存在, 请联系me@hydev.org");
            // TODO: 报错收集系统
            e.printStackTrace();
        } catch (RecentScoreNotEnoughException recentScoreNotEnough) {
            Main.getMessenger().respond(event, String.format("现在你%s模式的近期成绩只有%s个... 无法获取第%s个, 多玩玩再来看看吧!", OsuAPIUtils.getModeNameWithMode(recentScoreNotEnough.getMode()), recentScoreNotEnough.getLimit(), recentScoreNotEnough.getRequested()));
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.push";
    }
}
