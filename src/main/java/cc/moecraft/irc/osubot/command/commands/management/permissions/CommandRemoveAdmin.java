package cc.moecraft.irc.osubot.command.commands.management.permissions;

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
public class CommandRemoveAdmin extends Command
{
    public CommandRemoveAdmin()
    {
        super("removeadmin", "deop");
    }

    /**
     * 用法:
     *  !deop [用户名]       将一个用户取消管理员
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
        if (args.size() != 1)
        {
            return MultiLanguageText.directText("指令参数错误");
        }

        ArrayList<String> adminUsernames = Main.getIrcConfig().getAdminUsernames();

        adminUsernames.remove(args.get(0));

        Main.getIrcConfig().setAdminUsernames(adminUsernames);

        return MultiLanguageText.directText("已取消用户" + args.get(0) + "的管理员权限");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.command.deop";
    }
}
