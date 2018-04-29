package cc.moecraft.irc.osubot.osu.data;

import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
@EqualsAndHashCode(callSuper = true)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserData extends DataBase
{
    private long user_id;
    private String username;
    private long count300;
    private long count100;
    private long count50;
    private long playcount;
    private long ranked_score;
    private long total_score;
    private long pp_rank;
    private double level;
    private double pp_raw;
    private double accuracy;
    private long count_rank_ss;
    private long count_rank_ssh;
    private long count_rank_s;
    private long count_rank_sh;
    private long count_rank_a;
    private String country;
    private long pp_country_rank;

    /*
    两层的反射不知道怎么做

    private ArrayList<UserEvent> events;


    @Data
    public class UserEvent
    {
        private String display_html;
        private long beatmap_id;
        private long beatmapset_id;
        private String date;
        private long epicfactor;
    }*/
}
