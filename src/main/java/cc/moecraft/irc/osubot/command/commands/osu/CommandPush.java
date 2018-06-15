package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.ChannelCommand;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuUser;
import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.data.UserRecentData;
import cc.moecraft.irc.osubot.osu.data.UserScoreData;
import cc.moecraft.irc.osubot.osu.exceptions.BeatmapScoreNotEnoughException;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RecentScoreNotEnoughException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.utils.*;
import cc.moecraft.utils.ArrayUtils;
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
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        if (args.size() < 1) return MultiLanguageText.languageNode("commands.osu.push_error_input_username");

        int mode = OsuArgsUtils.getMode(args, 0);
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

        return process(event, sender, mode, username.replace(" ", "_"),
                MultiLanguageText.languageNode("commands.osu.push_format_user"));
    }

    @Override
    public MultiLanguageText channel(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        int mode;
        if (args.size() == 0) mode = 0;
        else if (args.size() == 1) mode = OsuArgsUtils.getMode(args, 0);
        else return MultiLanguageText.empty();

        if (mode == -1) return MultiLanguageText.empty();

        return process(event, sender, mode, channel.getName(),
                MultiLanguageText.languageNode("commands.osu.push_format_channel"));
    }

    public MultiLanguageText process(GenericMessageEvent event, User sender, int mode, String placeToSend, MultiLanguageText format)
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

            // 获取PP
            String ppMsg;

            try
            {
                UserScoreData score = Main.getWrapper().getScore(beatmapData);

                ppMsg = Math.round(score.getPp() * 100d) / 100d + " PP";
            }
            catch (BeatmapScoreNotEnoughException e)
            {
                ppMsg = Main.getMessenger().getText(event, MultiLanguageText.languageNode("keywords.unranked"));
            }

            format.putVariables(data, true)
                    .putVariables(beatmapData, true)
                    .putVariables(userData, true)
                    .putVariable("cm", modeName)
                    .putVariable("ct", time)
                    .putVariable("%ppmsg%", ppMsg);

            event.getBot().sendIRC().message(placeToSend, Main.getMessenger().getText(event, format));
        }
        catch (IllegalAccessException | RequiredParamIsNullException | MalformedURLException e)
        {
            e.printStackTrace();
            return MultiLanguageText.languageNode("errors.unknown_backend_error");
        }
        catch (JsonEmptyException e)
        {
            return MultiLanguageText.languageNode("errors.unknown_username")
                    .putVariable("username", info.getUsername());
        }
        catch (RecentScoreNotEnoughException recentScoreNotEnough)
        {
            return MultiLanguageText.languageNode("errors.scores_not_enough")
                    .putVariable("mode", OsuAPIUtils.getModeNameWithMode(recentScoreNotEnough.getMode()))
                    .putVariable("max", String.valueOf(recentScoreNotEnough.getLimit()))
                    .putVariable("current", String.valueOf(recentScoreNotEnough.getRequested()));
        }
        return MultiLanguageText.empty();
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.push";
    }
}
