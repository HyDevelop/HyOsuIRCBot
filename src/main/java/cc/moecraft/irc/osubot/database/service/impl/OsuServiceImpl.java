package cc.moecraft.irc.osubot.database.service.impl;

import cc.moecraft.irc.osubot.database.model.OsuStd;
import cc.moecraft.irc.osubot.database.service.OsuService;

import java.util.List;

public class OsuServiceImpl implements OsuService {

    private OsuStd osuStdDao = new OsuStd();

    @Override
    public List<OsuStd> getAllStd() {
        return osuStdDao.findAll();
    }

}
