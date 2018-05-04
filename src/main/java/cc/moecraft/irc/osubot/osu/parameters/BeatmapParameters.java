package cc.moecraft.irc.osubot.osu.parameters;

import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.parameters.tags.HttpParameter;
import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/03 创建!
 * Created by Hykilpikonna on 2018/05/03!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Builder @Data
@AllArgsConstructor
@NoArgsConstructor
public class BeatmapParameters extends ParametersBase
{
    // 不必要的参数
    @HttpParameter(required = false)
    private String since; // return all beatmaps ranked or loved since this date. Must be a MySQL date.

    @HttpParameter(required = false)
    private String s; // specify a beatmapset_id to return metadata from.

    @HttpParameter(required = false)
    private String b; // specify a beatmap_id to return metadata from.

    @HttpParameter(required = false)
    private String u; // specify a user_id or a username to return metadata from.

    @HttpParameter(required = false)
    private String m; // mode (0 = osu!, 1 = Taiko, 2 = CtB, 3 = osu!mania). Optional, maps of all modes are returned by default.

    @HttpParameter(required = false)
    private String a; // specify whether converted beatmaps are included (0 = not included, 1 = included). Only has an effect if m is chosen and not 0. Converted maps show their converted difficulty rating. Optional, default is 0.

    @HttpParameter(required = false)
    private String h; // the beatmap hash. It can be used, for instance, if you're trying to get what beatmap has a replay played in, as .osr replays only provide beatmap hashes (example of hash: a5b99395a42bd55bc5eb1d2411cbdf8b). Optional, by default all beatmaps are returned independently from the hash.

    @HttpParameter(required = false)
    private String type; // specify if u is a user_id or a username. Use string for usernames or id for user_ids. Optional, default behaviour is automatic recognition (may be problematic for usernames made up of digits only).

    @HttpParameter(required = false)
    private String limit; // amount of results. Optional, default and maximum are 500.

    @Override
    public String subURL()
    {
        return "beatmaps";
    }

    @Override
    public Class dataStorageClass()
    {
        return BeatmapData.class;
    }

    @Override
    public ReturnDataType returnDataType()
    {
        return ReturnDataType.LIST;
    }
}
