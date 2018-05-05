package cc.moecraft.irc.osubot.osu.parameters;

import cc.moecraft.irc.osubot.osu.data.UserRecentData;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import com.jfinal.core.paragetter.Para;
import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Builder @Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecentParameters extends ParametersBase
{
    // 必要的参数
    @HttpParameter(required = true)
    private String u;

    // 不必要的参数
    @HttpParameter(required = false)
    private String m;

    @HttpParameter(required = false)
    private String type;

    @HttpParameter(required = false)
    private String limit;

    @Override
    public String subURL()
    {
        return "get_user_recent";
    }

    @Override
    public Class dataStorageClass()
    {
        return UserRecentData.class;
    }

    @Override
    public ReturnDataType returnDataType()
    {
        return ReturnDataType.LIST;
    }
}
