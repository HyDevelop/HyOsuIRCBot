package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.command.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

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
     * 更新/查询玩家信息
     *  !u              更新STD数据
     *  !u s/std        更新STD数据
     *  !u t/taiko      更新Taiko数据
     *  !u m/mania      更新Mania数据
     *  !u c/ctb        更新CTB数据
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

    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.update";
    }
}
