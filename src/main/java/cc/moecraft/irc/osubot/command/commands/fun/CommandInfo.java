package cc.moecraft.irc.osubot.command.commands.fun;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.ChannelCommand;
import cc.moecraft.irc.osubot.command.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class CommandInfo extends Command implements ChannelCommand
{
    public CommandInfo()
    {
        super("info", "nfo");
    }

    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        if (args.size() < 1) return;

        switch (args.get(0))
        {
            case "变色":
                Main.getMessenger().respond(event, "浅黄：普通用户｜黄色：[https://osu.ppy.sh/p/support 捐赠玩家]｜红色：[https://osu.ppy.sh/wiki/QAT QAT（谱面质量保证团队）] 或是 [https://osu.ppy.sh/help/wiki/People/Global_Moderation_Team GMT（社群管理团队）]｜白色：你自己，或是一行使用 /me 指令的动作讯息｜深蓝色：私信｜绿色：有人提到你的名字（或是 Highlight）｜粉红色：IRC 萌萌机器人（ｂａｎｃｈｏｂｏｔ）");
                break;
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.fun.info";
    }

    @Override
    public void channel(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args) {
        run(event, sender, channel, command, args);
    }
}
