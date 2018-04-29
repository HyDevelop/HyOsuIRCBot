package cc.moecraft.irc.osubot.factory;


import cc.moecraft.irc.osubot.service.OsuStdService;
import cc.moecraft.irc.osubot.service.impl.OsuStdServiceImpl;

public class DAOFactory {

    public static OsuStdService getOsuStdService(){
        return new OsuStdServiceImpl();
    }
}
