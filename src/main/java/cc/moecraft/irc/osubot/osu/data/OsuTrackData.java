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
    private double pp_rank;
    private double level;
    private double count_rank_ss;
    private double accuracy;
    private double ranked_score;
    private double mode;
    private double count100;
    private double count_rank_s;
    private double playcount;
    private double count300;
    // private JsonArray newhs;
    private boolean levelup;
    private boolean exists;
    private double total_score;
    private double pp_raw;
    private double count50;
    private double count_rank_a;
    private boolean first;
    private String username;
}
