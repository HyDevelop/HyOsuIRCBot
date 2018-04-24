package cc.moecraft.irc.osubot.osu.data;

import lombok.*;

import java.util.ArrayList;

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
    private int user_id;
    private String username;
    private int count300;
    private int count100;
    private int count50;
    private int play_count;
    private int ranked_score;
    private int total_score;
    private int pp_rank;
    private double level;
    private double pp_raw;
    private double accuracy;
    private int count_rank_ss;
    private int count_rank_ssh;
    private int count_rank_s;
    private int count_rank_sh;
    private int count_rank_a;
    private String country;
    private int pp_country_rank;
    private ArrayList<UserEvent> events;

    @Data
    public class UserEvent
    {
        private String display_html;
        private int beatmap_id;
        private int beatmapset_id;
        private String date;
        private int epicfactor;
    }
}
