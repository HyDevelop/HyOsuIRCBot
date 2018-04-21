package cc.moecraft.irc.osubot;

import javafx.beans.value.ObservableObjectValue;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class OsuBot extends PircBot
{
    private BotProperties properties;

    public OsuBot(BotProperties properties)
    {
        setProperties(properties);
        setName(properties.getUsername());
    }

    /**
     * 消息监听器
     * @param channel 频道
     * @param sender 消息发送者
     * @param login ???
     * @param hostname 服务器名...?
     * @param message 消息
     */
    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        Main.getLogger().debug("收到消息: ");
        Main.getLogger().debug("- Channel:  " + channel);
        Main.getLogger().debug("- Sender:   " + sender);
        Main.getLogger().debug("- Login:    " + login);
        Main.getLogger().debug("- HostName: " + hostname);
        Main.getLogger().debug("- Message:  " + message);

        Main.getCommandManager().runCommand(message, sender);
    }

    /**
     * 连接到Properties里面设置的IRC服务器
     * @throws IOException
     * @throws IrcException
     */
    public void connect() throws IOException, IrcException
    {
        super.connect(properties.getIrcServerAddress(), properties.getIrcServerPort(), properties.getIrcServerPassword());
    }

    public BotProperties getProperties()
    {
        return properties;
    }

    public void setProperties(BotProperties properties)
    {
        this.properties = properties;
    }
}
