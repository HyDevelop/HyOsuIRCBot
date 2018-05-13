package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.command.ChannelCommand;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.osu.parameters.ParametersBase;
import cc.moecraft.irc.osubot.utils.ArrayUtils;
import cc.moecraft.irc.osubot.utils.StringUtils;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/12 创建!
 * Created by Hykilpikonna on 2018/05/12!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class CommandAchievement extends Command implements ChannelCommand
{
    public CommandAchievement()
    {
        super("achievement", "achievementinfo", "achieve", "成就");
    }

    /**
     * 用法:
     *  !achieve [成就名]         查询某个成就的信息
     *  !achieve [id]             查询某个成就ID的信息
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
        process(event, args, false);
    }

    @Override
    public void channel(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        process(event, args, true);
    }

    public static void process(GenericMessageEvent event, ArrayList<String> args, boolean isChannel)
    {
        if (args.size() < 1)
        {
            if (!isChannel) Main.getMessenger().respond(event, "%prefix%achieve [成就名或成就ID]");
            return;
        }

        String achievementName = ArrayUtils.getTheRestArgsAsString(args, 0);
        Achievement achievement;

        try
        {
            achievement = Main.getAchievementManager().findAchievementById(Integer.parseInt(achievementName));
        }
        catch (NumberFormatException ignored)
        {
            achievement = Main.getAchievementManager().findAchievementByName(achievementName);
        }

        Main.getMessenger().respond(event, String.format("成就: [%s (%s)]: %s",
                achievement.getName(),
                String.valueOf(achievement.getId()),
                achievement.getTutorial()));

        if (!isChannel)
        {
            CommandMap.process(event, Math.toIntExact(achievement.getRecommendedMap()));
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.map";
    }
}
