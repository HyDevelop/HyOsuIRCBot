package cc.moecraft.irc.osubot.osu.parameters;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
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
public class UserParameters extends ParametersBase
{
    // 必要的参数
    @HttpParameter(required = true)
    private String userIdOrName;

    // 不必要的参数
    @HttpParameter(required = false)
    private int mode;

    @HttpParameter(required = false)
    private String type;

    @HttpParameter(required = false)
    private int event_days;

    public UserParameters(String userIdOrName)
    {
        setUserIdOrName(userIdOrName);
    }

    @Override
    public String subURL()
    {
        return "user";
    }

    @Override
    public DataBase dataStorageObject()
    {
        return new UserData();
    }
}
