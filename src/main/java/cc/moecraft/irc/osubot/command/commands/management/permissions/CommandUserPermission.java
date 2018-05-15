package cc.moecraft.irc.osubot.command.commands.management.permissions;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
import cc.moecraft.irc.osubot.management.PermissionConfig;
import cc.moecraft.irc.osubot.management.PermissionGroup;
import cc.moecraft.irc.osubot.osu.OsuUser;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;

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
     * 用法:
     *  管理用户权限组:
     *
     *  !usr info [用户名]              查询用户权限信息
     *  !usr add [用户名] [权限组]      把用户加入权限组
     *  !usr remove [用户名] [权限组]   把用户从权限组中移除
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
        if (args.size() < 2)
        {
            return MultiLanguageText.directText("指令参数错误");
        }

        OsuUser target = new OsuUser(args.get(1));
        // TODO: 用Osu的API判断用户是否存在

        if (args.get(0).equals("info"))
        {
            return MultiLanguageText.directText("用户" + args.get(1) + "信息: " +
                    "[管理员: " + target.isAdmin() + ", " +
                    "继承权限组: " + PermissionConfig.groupListToNameList(target.getGroups()) + ", " +
                    "所有权限: " + PermissionConfig.permissionListToNameList(target.getAllPermissions()) + "]");
        }
        else if (args.size() == 3)
        {
            PermissionGroup permissionGroup = Main.getPermissionConfig().getGroup(args.get(2));

            if (permissionGroup == null)
                return MultiLanguageText.directText("无法添加, 权限组" + args.get(2) + "不存在");

            AtomicBoolean haveGroup = new AtomicBoolean(false);

            target.getGroups().forEach(permissionGroup1 -> {
                if (permissionGroup1.equals(permissionGroup)) haveGroup.set(true);
            });

            if (args.get(0).equals("add"))
            {
                if (haveGroup.get())
                    return MultiLanguageText.directText("添加失败, 玩家" + target.getUsername() + "已拥有权限组" + permissionGroup.getGroupName());

                target.getGroups().add(permissionGroup);
                Main.getPermissionConfig().setUserPermissionGroups(target);

                return MultiLanguageText.directText("添加成功, 权限组" + permissionGroup.getGroupName() + "已添加到玩家" + target.getUsername());
            }
            else if (args.get(0).equals("remove"))
            {
                if (!haveGroup.get())
                    return MultiLanguageText.directText("移除失败, 玩家" + target.getUsername() + "没有权限组" + permissionGroup.getGroupName());

                target.getGroups().remove(permissionGroup);
                Main.getPermissionConfig().setUserPermissionGroups(target);

                return MultiLanguageText.directText("移除成功, 权限组" + permissionGroup.getGroupName() + "从玩家" + target.getUsername() + "移除");
            }
        }

        return MultiLanguageText.directText("指令参数错误");
    }

    @Override
    public String permissionRequired()
    {
        return "irc.admin.permissionedit.users";
    }
}
