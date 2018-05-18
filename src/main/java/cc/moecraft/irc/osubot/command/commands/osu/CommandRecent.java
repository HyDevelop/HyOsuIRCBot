package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuUser;
import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.data.UserRecentData;
import cc.moecraft.irc.osubot.osu.data.UserScoreData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RecentScoreNotEnoughException;
import cc.moecraft.irc.osubot.osu.exceptions.RelatedScoreNotFoundException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
     *  查询最近的成绩:
     *
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
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        UsernameAndIndexAndMode info = getIndexAndModeWithArgs(sender, args);

        if (info.getIndex() > 50) return MultiLanguageText.languageNode("commands.osu.recent_error_greater_than_50");

        try {
            UserRecentData data = Main.getWrapper().getRecent(info);

            BeatmapData beatmapData = Main.getWrapper().getBeatmap(data);

            String ppMsg;

            try
            {
                UserScoreData scoreData = Main.getWrapper().getScore(info, data);

                if (scoreData.getPp() == null)
                    ppMsg = Main.getMessenger().getText(event, MultiLanguageText.languageNode("keywords.unranked"));
                else
                    ppMsg = String.valueOf(Math.round(scoreData.getPp() * 100d) / 100d) + "pp";
            }
            catch (RelatedScoreNotFoundException e)
            {
                // TODO: @dullwolf PP计算
                ppMsg = Main.getMessenger().getText(event, MultiLanguageText.languageNode("keywords.unranked"));
            }

            // 四舍五入
            ReflectUtils.roundAllNumbers(data, 1);
            ReflectUtils.roundAllNumbers(beatmapData, 1);

            // TODO: Mods显示

            return MultiLanguageText.languageNode("commands.osu.recent_format")
                    .putVariables(data, true)
                    .putVariables(beatmapData, true)
                    .putVariable("%cm%", OsuAPIUtils.getModeNameWithMode(beatmapData.getMode()))
                    .putVariable("%ca%", String.valueOf(Math.round(data.getAcc(Main.getWrapper(), beatmapData.getMode()) * 10000d) / 100d))
                    .putVariable("%cscore%", new DecimalFormat("#,###").format(Math.round(data.getScore())))
                    .putVariable("%ppmsg%", ppMsg);

        }
        catch (IllegalAccessException | RequiredParamIsNullException | MalformedURLException e)
        {
            e.printStackTrace();
            return MultiLanguageText.languageNode("errors.unknown_backend_error");
        }
        catch (JsonEmptyException e)
        {
            return MultiLanguageText.languageNode("errors.unknown_username");
        }
        catch (RecentScoreNotEnoughException recentScoreNotEnough)
        {
            return MultiLanguageText.languageNode("errors.scores_not_enough")
                    .putVariable("mode", OsuAPIUtils.getModeNameWithMode(recentScoreNotEnough.getMode()))
                    .putVariable("max", String.valueOf(recentScoreNotEnough.getLimit()))
                    .putVariable("current", String.valueOf(recentScoreNotEnough.getRequested()));
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

        public UsernameAndIndexAndMode(int index, int mode, String username)
        {
            super(mode, username);
            this.index = index;
        }
    }
}
