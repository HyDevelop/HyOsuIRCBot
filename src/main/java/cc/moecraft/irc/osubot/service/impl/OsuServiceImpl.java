package cc.moecraft.irc.osubot.service.impl;

import cc.moecraft.irc.osubot.model.Osu;
import cc.moecraft.irc.osubot.service.OsuService;
import io.jboot.core.rpc.annotation.JbootrpcService;

import java.util.List;

@JbootrpcService
public class OsuServiceImpl implements OsuService{

    private Osu osuDao = new Osu();

    @Override
    public List<Osu> getAll() {
        return osuDao.findAll();
    }
}
