package cc.moecraft.irc.osubot.osu.data;

import lombok.*;

/**
 * 此类由 Hykilpikonna 的 JSONClassGenerator 生成!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
@EqualsAndHashCode(callSuper = true)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class OsuTrackData extends DataBase
{
    private long pp_rank;
    private long level;
    private long count_rank_ss;
    private long accuracy;
    private long ranked_score;
    private long mode;
    private long count100;
    private long count_rank_s;
    private long playcount;
    private long count300;
    private com.alibaba.fastjson.JSONArray newhs;
    private boolean levelup;
    private boolean exists;
    private long total_score;
    private long pp_raw;
    private long count50;
    private long count_rank_a;
    private boolean first;
    private String username;
}
