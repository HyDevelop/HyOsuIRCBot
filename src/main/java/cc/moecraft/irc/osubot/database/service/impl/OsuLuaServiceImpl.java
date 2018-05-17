package cc.moecraft.irc.osubot.database.service.impl;

import cc.moecraft.irc.osubot.database.model.OsuLua;
import cc.moecraft.irc.osubot.database.service.OsuLuaService;

public class OsuLuaServiceImpl implements OsuLuaService {

    private OsuLua osuLuaDao = new OsuLua();


    @Override
    public OsuLua add(long userId,String username,int mode,String lua) {
        OsuLua model = new OsuLua();
        model.setUserId(userId);
        model.setUsername(username);
        model.setMode(mode);
        model.setLanguage(lua);
        return model.save() ? osuLuaDao.findById(userId) : null;
    }

    @Override
    public boolean update(long userId,String username) {
        OsuLua model = osuLuaDao.findById(userId);
        model.setUsername(username);
        return model.save();
    }

    @Override
    public OsuLua get(String username) {
        return osuLuaDao.findFirstByColumn("username",username);
    }
}
