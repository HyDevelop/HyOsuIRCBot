package cc.moecraft.irc.osubot.database.service;

import cc.moecraft.irc.osubot.database.model.OsuLua;

public interface OsuLuaService {

    /**
     * 插入语言配置
     */
    OsuLua add(long userId,String username,int mode,String lua);

    /**
     * 更新
     */
    boolean update(long userId,String username);

    /**
     * 根据名字获取
     */
    OsuLua get(String username);


}
