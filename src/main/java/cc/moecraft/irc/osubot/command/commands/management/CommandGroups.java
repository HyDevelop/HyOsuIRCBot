package cc.moecraft.irc.osubot.command.commands.management;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.Messenger;
import cc.moecraft.irc.osubot.management.Permission;
import cc.moecraft.irc.osubot.management.PermissionConfig;
import cc.moecraft.irc.osubot.management.PermissionGroup;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class CommandGroups extends Command
{
    public CommandGroups()
    {
        super("groups", "grp");
    }

    /**
     * 指令帮助:
     *  - !grp info [名字]                   查询权限组 2
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
        if (args.size() == 2)  // 创建移除查询权限组
        {
            if (args.get(0).equals("create"))
            {
                if (Main.getPermissionConfig().getGroup(args.get(1)) == null)
                {
                    PermissionGroup group = new PermissionGroup(args.get(1));

                    Main.getPermissionConfig().setGroup(group);

                    Main.getMessenger().respond(event, "权限组" + args.get(1) + "已成功创建");
                    return;
                }
                else
                {
                    Main.getMessenger().respond(event, "无法创建, 权限组" + args.get(1) + "已存在");
                    return;
                }
            }
            else if (args.get(0).equals("remove"))
            {
                if (Main.getPermissionConfig().getGroup(args.get(1)) != null)
                {
                    PermissionGroup group = Main.getPermissionConfig().getGroup(args.get(1));

                    Main.getPermissionConfig().removeGroup(group);

                    Main.getMessenger().respond(event, "权限组" + args.get(1) + "已成功删除");
                    return;
                }
                else
                {
                    Main.getMessenger().respond(event, "无法移除, 权限组" + args.get(1) + "不存在");
                    return;
                }
            }
            else if (args.get(0).equals("info"))
            {
                if (Main.getPermissionConfig().getGroup(args.get(1)) != null)
                {
                    PermissionGroup group = Main.getPermissionConfig().getGroup(args.get(1));

                    Main.getMessenger().respond(event, "权限组" + args.get(1) + "信息:");
                    Main.getMessenger().respond(event, "- 继承权限组: " + PermissionConfig.groupListToNameList(group.getContainings()));
                    Main.getMessenger().respond(event, "- 单独权限:   " + PermissionConfig.permissionListToNameList(group.getThisGroupPermissions()));
                    Main.getMessenger().respond(event, "- 所有权限:   " + PermissionConfig.permissionListToNameList(group.getAllPermissions()));
                    return;
                }
                else
                {
                    Main.getMessenger().respond(event, "无法查询, 权限组" + args.get(1) + "不存在");
                    return;
                }
            }
        }
        else if (args.size() == 4) // 编辑权限组
        {
            if (Main.getPermissionConfig().getGroup(args.get(2)) != null)
            {
                PermissionGroup group = Main.getPermissionConfig().getGroup(args.get(2));

                if (args.get(0).equals("perm"))
                {
                    Permission permission = new Permission(args.get(3));

                    if (args.get(1).equals("add"))
                    {
                        if (!group.getThisGroupPermissions().contains(permission))
                        {
                            group.getThisGroupPermissions().add(permission);

                            Main.getPermissionConfig().setGroup(group);

                            Main.getMessenger().respond(event, "已添加权限: " + permission.toString());
                            return;
                        }
                        else
                        {
                            Main.getMessenger().respond(event, "无法添加, 权限" + permission.toString() + "已存在");
                            return;
                        }
                    }
                    else if (args.get(1).equals("remove"))
                    {
                        if (group.getThisGroupPermissions().contains(permission))
                        {
                            group.getThisGroupPermissions().remove(permission);

                            Main.getPermissionConfig().setGroup(group);

                            Main.getMessenger().respond(event, "已移除权限: " + permission.toString());
                            return;
                        }
                        else
                        {
                            Main.getMessenger().respond(event, "无法移除, 权限" + permission.toString() + "不存在");
                            return;
                        }
                    }
                }
                else if (args.get(0).equals("group"))
                {
                    PermissionGroup newGroup = Main.getPermissionConfig().getGroup(args.get(3));

                    if (newGroup == null)
                    {
                        Main.getMessenger().respond(event, "无法编辑, 权限组" + args.get(3) + "不存在");
                        return;
                    }

                    if (args.get(1).equals("add"))
                    {
                        if (!group.getContainings().contains(newGroup))
                        {
                            group.getContainings().add(newGroup);

                            Main.getPermissionConfig().setGroup(group);

                            Main.getMessenger().respond(event, "已添加权限组继承: " + newGroup.getGroupName());
                            return;
                        }
                        else
                        {
                            Main.getMessenger().respond(event, "无法添加继承, 权限组" + newGroup.getGroupName() + "已存在");
                            return;
                        }
                    }
                    else if (args.get(1).equals("remove"))
                    {
                        if (group.getContainings().contains(newGroup))
                        {
                            group.getContainings().remove(newGroup);

                            Main.getPermissionConfig().setGroup(group);

                            Main.getMessenger().respond(event, "已移除权限组继承: " + newGroup.getGroupName());
                            return;
                        }
                        else
                        {
                            Main.getMessenger().respond(event, "无法移除继承, 权限组" + newGroup.getGroupName() + "已存在");
                            return;
                        }
                    }
                }
            }
            else
            {
                Main.getMessenger().respond(event, "无法编辑, 权限组" + args.get(2) + "不存在");
                return;
            }
        }
        Main.getMessenger().respond(event, "指令参数错误");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.permissionedit.groups";
    }
}
