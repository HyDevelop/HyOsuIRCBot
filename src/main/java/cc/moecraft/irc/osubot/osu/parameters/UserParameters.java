package cc.moecraft.irc.osubot.osu.parameters;

import cc.moecraft.irc.osubot.osu.data.DataBase;
import cc.moecraft.irc.osubot.osu.data.UserData;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
@EqualsAndHashCode(callSuper = true)
@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class UserParameters extends ParametersBase
{
    // 必要的参数
    @HttpParameter(required = true)
    private String u;

    // 不必要的参数
    @HttpParameter(required = false)
    private String mode;

    @HttpParameter(required = false)
    private String type;

    @HttpParameter(required = false)
    private String event_days;

    public UserParameters(String userIdOrName)
    {
        setU(userIdOrName);
    }

    @Override
    public String subURL()
    {
        return "user";
    }

    @Override
    public Class dataStorageClass()
    {
        return UserData.class;
    }

    @Override
    public ReturnDataType returnDataType()
    {
        return ReturnDataType.SINGLETON_LIST;
    }
}
