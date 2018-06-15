package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import cc.moecraft.irc.osubot.language.RandomMLT;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.OsuUser;
import cc.moecraft.irc.osubot.osu.data.OsuTrackData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static cc.moecraft.irc.osubot.utils.OsuArgsUtils.getUsernameAndModeWithArgs;

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
     * 用法:
     *  更新玩家信息:
     *
     *  !u              更新自己的信息
     *  !u [用户名]     更新其他玩家信息
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
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        OsuUser.UsernameAndMode usernameAndMode = getUsernameAndModeWithArgs(sender, args);

        try
        {
            OsuTrackData osuTrackData = OsuUser.getOsuTrackData(usernameAndMode);

            if (!osuTrackData.getExists()) throw new JsonEmptyException();

            // 四舍五入
            ReflectUtils.roundAllNumbers(osuTrackData, 1);

            // 新玩家
            if (osuTrackData.getFirst() && usernameAndMode.isSelf())
            {
                Main.getMessenger().respondIRC(event, MultiLanguageText.languageNode("update_message_newbie_1"));
                return MultiLanguageText.languageNode("update_message_newbie_2");
            }

            // 获取Mode名字
            String modeName = OsuAPIUtils.getModeNameWithMode(usernameAndMode.getMode());

            return MultiLanguageText.languageNode("commands.osu.update_format")
                    .putVariables(osuTrackData, true, true)
                    .putVariable("%cm%", modeName)
                    .putVariable("%clink%", OsuUser.getOsuTrackLink(usernameAndMode))
                    .putVariable("%crank%", (osuTrackData.getPpRank() < 0 ? "↑" : "↓") + Math.abs(osuTrackData.getPpRank()))
                    .setPrefix(getPrefix(osuTrackData));
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
        return "irc.user.regular.osu.update";
    }

    /**
     * 获取适应玩家环境的前缀消息
     *
     * 例子：
     *  +0  +0  ： “多玩玩再来看吧!”
     *  +20 +400： “进步了.. 加油!”
     *  +99 +999： “w.. 大..大佬!”
     *
     * @param osuTrackData 数据
     * @return 前缀消息
     */
    public RandomMLT getPrefix(OsuTrackData osuTrackData)
    {
        if (osuTrackData.getPpRaw() <= 0)
        {
            return new RandomMLT("commands.osu.update_prefix_0");
        }
        else if (osuTrackData.getPpRaw() <= 20)
        {
            return new RandomMLT("commands.osu.update_prefix_L20");
        }
        else if (osuTrackData.getPpRaw() > 20)
        {
            return new RandomMLT("commands.osu.update_prefix_H20");
        }

        return null; // 不可能发生的情况
    }
}
