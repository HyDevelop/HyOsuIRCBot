package cc.moecraft.irc.osubot.command.commands.management;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.management.PermissionConfig;
import cc.moecraft.irc.osubot.management.PermissionGroup;
import cc.moecraft.irc.osubot.osu.OsuUser;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class CommandUserPermission extends Command
{
    public CommandUserPermission()
    {
        super("userpermission", "usp", "usr");
    }

    /**
     * 指令帮助:
     *  - !usr info [用户名]              查询用户权限信息
     *  - !usr add [用户名] [权限组]      把用户加入权限组
     *  - !usr remove [用户名] [权限组]   把用户从权限组中移除
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
        if (args.size() < 2)
        {
            Main.getMessenger().respond(event, "指令参数错误");
            return;
        }

        OsuUser target = new OsuUser(args.get(1));
        // TODO: 用Osu的API判断用户是否存在

        if (args.get(0).equals("info"))
        {
            Main.getMessenger().respond(event, "用户" + args.get(1) + "信息:");
            Main.getMessenger().respond(event, "- 管理员:     " + target.isAdmin());
            Main.getMessenger().respond(event, "- 继承权限组: " + PermissionConfig.groupListToNameList(target.getGroups()));
            Main.getMessenger().respond(event, "- 所有权限:   " + PermissionConfig.permissionListToNameList(target.getAllPermissions()));
            return;
        }
        else if (args.size() == 3)
        {
            PermissionGroup permissionGroup = Main.getPermissionConfig().getGroup(args.get(2));

            if (permissionGroup == null)
            {
                Main.getMessenger().respond(event, "无法添加, 权限组" + args.get(2) + "不存在");
                return;
            }

            AtomicBoolean haveGroup = new AtomicBoolean(false);

            target.getGroups().forEach(permissionGroup1 -> {
                if (permissionGroup1.equals(permissionGroup)) haveGroup.set(true);
            });

            if (args.get(0).equals("add"))
            {
                if (haveGroup.get())
                {
                    Main.getMessenger().respond(event, "添加失败, 玩家" + target.getUsername() + "已拥有权限组" + permissionGroup.getGroupName());
                    return;
                }

                target.getGroups().add(permissionGroup);
                Main.getPermissionConfig().setUserPermissionGroups(target);

                Main.getMessenger().respond(event, "添加成功, 权限组" + permissionGroup.getGroupName() + "已添加到玩家" + target.getUsername());
                return;
            }
            else if (args.get(0).equals("remove"))
            {
                if (!haveGroup.get())
                {
                    Main.getMessenger().respond(event, "移除失败, 玩家" + target.getUsername() + "没有权限组" + permissionGroup.getGroupName());
                    return;
                }

                target.getGroups().remove(permissionGroup);
                Main.getPermissionConfig().setUserPermissionGroups(target);

                Main.getMessenger().respond(event, "移除成功, 权限组" + permissionGroup.getGroupName() + "从玩家" + target.getUsername() + "移除");
                return;

            }
        }

        Main.getMessenger().respond(event, "指令参数错误");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.permissionedit.users";
    }
}
