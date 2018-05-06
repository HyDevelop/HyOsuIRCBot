package cc.moecraft.irc.osubot.osu.parameters;

import cc.moecraft.irc.osubot.osu.data.UserScoreData;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Builder @AllArgsConstructor @Data @NoArgsConstructor
public class UserScoreParameters extends ParametersBase
{
    // 必要的参数
    @HttpParameter(required = true)
    private String b;

    // 不必要的参数
    @HttpParameter(required = false)
    private String u;

    @HttpParameter(required = false)
    private String m;

    @HttpParameter(required = false)
    private String mods;

    @HttpParameter(required = false)
    private String type;

    @HttpParameter(required = false)
    private String limit;

    @Override
    public String subURL()
    {
        return "scores";
    }

    @Override
    public Class dataStorageClass()
    {
        return UserScoreData.class;
    }

    @Override
    public ReturnDataType returnDataType()
    {
        return ReturnDataType.LIST;
    }
}
