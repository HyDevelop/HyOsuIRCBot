package cc.moecraft.irc.osubot.database.factory;

import cc.moecraft.irc.osubot.database.service.OsuStdService;
import cc.moecraft.irc.osubot.database.service.impl.OsuStdServiceImpl;

public class DAOFactory {

    public static OsuStdService getOsuStdService(){
        return new OsuStdServiceImpl();
    }
}
