package com.dullwolf;

import cc.moecraft.irc.osubot.management.Permission;
import cc.moecraft.logger.DebugLogger;

/**
 * 此类由 Hykilpikonna 在 2018/04/22 创建!
 * Created by Hykilpikonna on 2018/04/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class PermissionAlgorismTest
{
    private static DebugLogger logger = new DebugLogger("Test", true);
    
    public static void main(String[] args)
    {
        Permission permission = new Permission("irc.admin.*");

        logger.debug("" + permission.hasPermission("irc.admin.*"));
        logger.debug("" + permission.hasPermission("irc.admin.random.perm"));
        logger.debug("" + permission.hasPermission("irc.user.use"));
    }
}
