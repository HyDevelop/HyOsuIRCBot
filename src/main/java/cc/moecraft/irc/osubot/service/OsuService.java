package cc.moecraft.irc.osubot.service;


import cc.moecraft.irc.osubot.model.Osu;

import java.util.List;

public interface OsuService {

    /**
     * 获取全部OSU玩家名字
     */
    List<Osu> getAll();
}
