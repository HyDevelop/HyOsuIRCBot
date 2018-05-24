package cc.moecraft.irc.osubot.command.commands.management;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class CommandReload extends Command
{
    public CommandReload()
    {
        super("reload");
    }

    /**
     * 用法:
     *  !reload         重载配置
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
        Main.getMessenger().respondIRC(event, MultiLanguageText.directText("开始重载..."));
        long start = System.currentTimeMillis();

        Main.getIrcConfig().reload();
        Main.getPermissionConfig().reload();

        return MultiLanguageText.directText("重载完成! 耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.managing.reload";
    }
}
