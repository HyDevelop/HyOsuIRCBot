package cc.moecraft.irc.osubot.command.commands.management;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.Messenger;
import cc.moecraft.irc.osubot.management.PermissionGroup;
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
public class CommandGroups extends Command
{
    public CommandGroups()
    {
        super("groups", "grp");
    }

    /**
     * 指令帮助:
     *  - !grp create [名字]                 创建权限组 2
     *  - !grp remove [名字]                 移除权限组 2
     *  - !grp perm add [名字] [权限]        添加权限   4
     *  - !grp perm remove [名字] [权限]     移除权限   4
     *  - !grp group add [名字] [权限组]     添加继承   4
     *  - !grp group remove [名字] [权限组]  移除继承   4
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
        if (args.size() == 2)  // 创建或者移除权限组
        {
            if (args.get(0).equals("create"))
            {
                if (Main.getPermissionConfig().getGroup(args.get(1)) == null)
                {
                    PermissionGroup group = new PermissionGroup(args.get(1));

                    Main.getPermissionConfig().setGroup(group);
                }
                else
                {
                    Main.getMessenger().respond(event, "无法创建, 权限组已存在");
                }
            }
            else if (args.get(0).equals("remove"))
            {
                if (Main.getPermissionConfig().getGroup(args.get(1)) != null)
                {
                    PermissionGroup group = new PermissionGroup(args.get(1));

                    Main.getPermissionConfig().removeGroup(group);
                }
                else
                {
                    Main.getMessenger().respond(event, "无法移除, 权限组不存在");
                }
            }
        }
        else if (args.size() == 4)
        {

        }
        Main.getMessenger().respond(event, "指令参数错误");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.permissionedit.groups";
    }
}
