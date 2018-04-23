package cc.moecraft.irc.osubot.factory;


import cc.moecraft.irc.osubot.service.OsuService;
import cc.moecraft.irc.osubot.service.impl.OsuServiceImpl;

public class DAOFactory {

    public static OsuService getOsuService(){
        return new OsuServiceImpl();
    }
}
