package cc.moecraft.irc.osubot.osu.parameters;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.data.UserData;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserParameters extends ParameterBase
{
    // 必要的参数
    private String userIdOrName;

    // 不必要的参数
    private int mode;

    public UserParameters(String userIdOrName)
    {
        setUserIdOrName(userIdOrName);
    }

    @Override
    public String getSubURL()
    {
        return "user";
    }

    @Override
    public DataBase getDataStorage()
    {
        return new UserData();
    }
}
