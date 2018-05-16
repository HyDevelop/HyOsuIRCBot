package cc.moecraft.irc.osubot.database.factory;

import cc.moecraft.irc.osubot.database.service.OsuLuaService;
import cc.moecraft.irc.osubot.database.service.OsuService;
import cc.moecraft.irc.osubot.database.service.impl.OsuLuaServiceImpl;
import cc.moecraft.irc.osubot.database.service.impl.OsuServiceImpl;

public class DAOFactory {

    public static OsuService getOsuService(){
        return new OsuServiceImpl();
    }

    public static OsuLuaService getOsuLuaService(){
        return new OsuLuaServiceImpl();
    }
}
