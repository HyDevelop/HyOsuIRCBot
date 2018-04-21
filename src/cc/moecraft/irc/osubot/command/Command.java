package cc.moecraft.irc.osubot.command;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
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
     * 执行指令
     * @param senderUsername 发送者的irc用户名 ( 在osu的irc服务器的话就是osu的用户名 )
     * @param command 指令名 ( 不包含指令参数 )
     * @param args 指令参数 ( 不包含指令名 )
     */
    public abstract void run(String senderUsername, String command, ArrayList<String> args); //TODO 添加用户系统并把这个String类的用户改成CommandSender类的用户

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