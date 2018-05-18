package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuUser;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static cc.moecraft.irc.osubot.utils.ArrayUtils.getUsernameAndModeWithArgs;

/**
 * 此类由 Hykilpikonna 在 2018/04/23 创建!
 * Created by Hykilpikonna on 2018/04/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandStats extends Command
{
    public CommandStats()
    {
        super("stats", "s");
    }

    /**
     * 用法:
     *  查询玩家信息:
     *
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
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        OsuUser.UsernameAndMode usernameAndMode = getUsernameAndModeWithArgs(sender, args);

        try
        {
            UserData userData = OsuUser.getData(usernameAndMode);

            // 四舍五入
            ReflectUtils.roundAllNumbers(userData, 1);

            // SS和SSH加起来, S和SH加起来
            userData.setCountRankS(userData.getCountRankS() + userData.getCountRankSh());
            userData.setCountRankSs(userData.getCountRankSs() + userData.getCountRankSsh());

            // 获取Mode名字
            String modeName = OsuAPIUtils.getModeNameWithMode(usernameAndMode.getMode());

            return MultiLanguageText.languageNode("commands.osu.stats_format")
                    .putVariables(userData, true)
                    .putVariable("mode", modeName);
        }
        catch (IllegalAccessException | RequiredParamIsNullException | MalformedURLException e)
        {
            e.printStackTrace();
            return MultiLanguageText.languageNode("errors.unknown_backend_error");
        }
        catch (JsonEmptyException e)
        {
            return MultiLanguageText.languageNode("errors.unknown_username")
                    .putVariable("username", usernameAndMode.getUsername());
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.stats";
    }
}
