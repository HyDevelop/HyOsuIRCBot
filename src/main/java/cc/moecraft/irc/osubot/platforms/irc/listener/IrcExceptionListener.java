package cc.moecraft.irc.osubot.platforms.irc.listener;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ExceptionEvent;
import org.pircbotx.hooks.events.ListenerExceptionEvent;

/**
 * 此类由 Hykilpikonna 在 2018/05/19 创建!
 * Created by Hykilpikonna on 2018/05/19!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class IrcExceptionListener extends ListenerAdapter
{
    @Override
    public void onException(ExceptionEvent event) throws Exception
    {
        event.getException().printStackTrace();
    }

    @Override
    public void onListenerException(ListenerExceptionEvent event) throws Exception
    {
        event.getException().printStackTrace();
    }
}
