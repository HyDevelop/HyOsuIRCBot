package cc.moecraft.irc.osubot.language;

import org.pircbotx.hooks.types.GenericMessageEvent;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class Messenger
{
    public Messenger()
    {

    }

    public void respond(GenericMessageEvent event, String text)
    {
        event.respond(text);
    }
}
