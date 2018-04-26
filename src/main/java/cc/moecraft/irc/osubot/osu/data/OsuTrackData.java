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
    public long pp_rank;
    public long level;
    public long count_rank_ss;
    public long accuracy;
    public long ranked_score;
    public long mode;
    public long count100;
    public long count_rank_s;
    public long playcount;
    public long count300;
    public com.alibaba.fastjson.JSONArray newhs;
    public boolean levelup;
    public boolean exists;
    public long total_score;
    public long pp_raw;
    public long count50;
    public long count_rank_a;
    public boolean first;
    public String username;
}
