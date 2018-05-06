package cc.moecraft.irc.osubot.osu.parameters;

import cc.moecraft.irc.osubot.osu.data.UserBestData;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/06 创建!
 * Created by Hykilpikonna on 2018/05/06!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Builder @Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBestParameters extends ParametersBase
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
        return "user_best";
    }

    @Override
    public Class dataStorageClass()
    {
        return UserBestData.class;
    }

    @Override
    public ReturnDataType returnDataType()
    {
        return ReturnDataType.LIST;
    }
}
