package cc.moecraft.irc.osubot.service;

import cc.moecraft.irc.osubot.model.Osu;

import java.util.List;

public class OsuServiceImpl {

    private static Osu osuDao = new Osu();

    public static List<Osu> getAll() {
        return osuDao.findAll();
    }
}
