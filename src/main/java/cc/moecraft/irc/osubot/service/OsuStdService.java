package cc.moecraft.irc.osubot.service;

import cc.moecraft.irc.osubot.model.OsuStd;

import java.util.List;

public interface OsuStdService {

    /**
     * 获取所有STD模式的OSU 玩家数据
     */
    List<OsuStd> getAll();

    /**
     * 获取所有STD模式的OSU玩家最大的userId
     */
    long getMaxId();

    /**
     * 库表所有用户
     */
    long totalCount();

    /**
     * 根据ID查找用户是否存在数据库
     */
    boolean getExistById(long userId);

    /**
     * 根据userId异步保存用户玩家信息
     */
    void asyncSave(long size,long nextId);

}
