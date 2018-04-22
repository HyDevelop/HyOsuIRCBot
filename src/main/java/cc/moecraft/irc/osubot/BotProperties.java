package cc.moecraft.irc.osubot;

import cc.moecraft.irc.osubot.listener.CommandListener;
import org.pircbotx.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2018/04/20 创建!
 * Created by Hykilpikonna on 2018/04/20!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class BotProperties
{
    // 服务器连接设置 ( 连接irc必须有的参数 )
    private String username;            // 用户名
    private String ircServerAddress;    // 服务器地址
    private int ircServerPort;          // 服务器端口
    private String ircServerPassword;   // 服务器密码 ( Osu其实是Osu的irc账号的独立密码 )

    // 服务器连接设置 ( 连接, 但是不必须有的参数 )
    private ArrayList<String> autoJoinChannels; // 自动加入的频道

    // 服务器管理设置 ( 不必须有的参数 )
    private ArrayList<String> botAdminUsername; // 管理员用户名列表

    public Configuration toPircConfiguration()
    {
        Configuration.Builder configurationBuilder =  new Configuration.Builder()
                .setName(username)
                .addServer(ircServerAddress, ircServerPort)
                .addListener(new CommandListener());

        if (autoJoinChannels != null) configurationBuilder.addAutoJoinChannels(autoJoinChannels);

        if (ircServerPassword == null || ircServerPassword.equals(""))
            return configurationBuilder.buildConfiguration();
        else return configurationBuilder.setServerPassword(ircServerPassword).buildConfiguration();
    }

    public String getUsername()
    {
        return username;
    }

    public BotProperties setUsername(String username)
    {
        this.username = username;
        return this;
    }

    public String getIrcServerAddress()
    {
        return ircServerAddress;
    }

    public BotProperties setIrcServerAddress(String ircServerAddress)
    {
        this.ircServerAddress = ircServerAddress;
        return this;
    }

    public int getIrcServerPort()
	{
        return ircServerPort;
    }

    public BotProperties setIrcServerPort(int ircServerPort)
	{
        this.ircServerPort = ircServerPort;
        return this;
    }

    public String getIrcServerPassword()
	{
        return ircServerPassword;
    }

    public BotProperties setIrcServerPassword(String ircServerPassword)
	{
        this.ircServerPassword = ircServerPassword;
        return this;
    }

    public ArrayList<String> getBotAdminUsername()
	{
        return botAdminUsername;
    }

    public BotProperties setBotAdminUsername(ArrayList<String> botAdminUsername)
	{
        this.botAdminUsername = botAdminUsername;
        return this;
    }

    public ArrayList<String> getAutoJoinChannels()
    {
        return autoJoinChannels;
    }

    public BotProperties setAutoJoinChannels(ArrayList<String> autoJoinChannels)
    {
        this.autoJoinChannels = autoJoinChannels;
        return this;
    }
}
