package cc.moecraft.irc.osubot.database.factory;

import cc.moecraft.irc.osubot.database.service.OsuService;
import cc.moecraft.irc.osubot.database.service.impl.OsuServiceImpl;

public class DAOFactory {

    public static OsuService getOsuService(){
        return new OsuServiceImpl();
    }
}
