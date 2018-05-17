package cc.moecraft.irc.osubot.database.service;

import cc.moecraft.irc.osubot.database.model.OsuStd;

import java.util.List;

public interface OsuService {

    /**
     * 获取所有STD模式的OSU 玩家数据
     */
    List<OsuStd> getAllStd();


}
