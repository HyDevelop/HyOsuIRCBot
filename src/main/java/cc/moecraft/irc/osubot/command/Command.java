package cc.moecraft.irc.osubot.command;

import cc.moecraft.irc.osubot.language.MultiLanguageText;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public abstract class Command
{
    private String name;                // 指令名
    private ArrayList<String> alias;    // 其他指向这个指令的指令名

    /**
     * 指令构造器
     * @param name 指令名
     * @param alias 其他指令名
     */
    public Command(String name, ArrayList<String> alias)
    {
        this.name = name;
        this.alias = alias;
    }

    /**
     * 指令构造器封装
     * @param name 指令名
     */
    public Command(String name)
    {
        this(name, new ArrayList<>());
    }

    /**
     * 指令构造器封装
     * @param name 指令名
     * @param alias 其他指令名
     */
    public Command(String name, String ... alias)
    {
        this(name, new ArrayList<>(Arrays.asList(alias)));
    }

    /**
     * 执行指令
     * @param event 事件
     * @param sender 发送者的irc用户 ( 在osu的irc服务器的话用户名就是osu的用户名 )
     * @param channel 频道
     * @param command 指令名 ( 不包含指令参数 )
     * @param args 指令参数 ( 不包含指令名 )
     * @return 发送回去的消息
     */
    public abstract MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args);

    /**
     * 获取需要的权限
     * @return 需要的权限
     */
    public abstract String permissionRequired();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<String> getAlias()
    {
        return alias;
    }

    public void setAlias(ArrayList<String> alias)
    {
        this.alias = alias;
    }
}
