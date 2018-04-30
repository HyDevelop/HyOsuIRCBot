package cc.moecraft.irc.osubot.service.impl;

import cc.moecraft.irc.osubot.model.OsuStd;
import cc.moecraft.irc.osubot.service.OsuStdService;
import io.jboot.Jboot;
import io.jboot.component.redis.JbootRedis;

import java.util.List;

public class OsuStdServiceImpl implements OsuStdService {

    private JbootRedis redis = Jboot.me().getRedis();

    private OsuStd osuStdDao = new OsuStd();

    @Override
    public List<OsuStd> getAll() {
        return osuStdDao.findAll();
    }

    @Override
    public long getMaxId() {
        String sql = "select ifnull(max(user_id),0) as userId from osu_std ";
        return Long.parseLong(osuStdDao.findFirst(sql).getStr("userId"));
    }

    @Override
    public boolean getExistById(long userId) {
        String sql = "select count(*) as total from osu_std where user_id = "+ userId;
        return osuStdDao.findFirst(sql).getLong("total") > 0;
    }
}
