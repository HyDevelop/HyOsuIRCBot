package cc.moecraft.irc.osubot.osu.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

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
    @SerializedName("user_id")
    @Expose
    private long userId;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("count300")
    @Expose
    private long count300;

    @SerializedName("count100")
    @Expose
    private long count100;

    @SerializedName("count50")
    @Expose
    private long count50;

    @SerializedName("playcount")
    @Expose
    private long playCount;

    @SerializedName("ranked_score")
    @Expose
    private long rankedScore;

    @SerializedName("total_score")
    @Expose
    private long totalScore;

    @SerializedName("pp_rank")
    @Expose
    private long ppRank;

    @SerializedName("level")
    @Expose
    private double level;

    @SerializedName("pp_raw")
    @Expose
    private double ppRaw;

    @SerializedName("accuracy")
    @Expose
    private double accuracy;

    @SerializedName("count_rank_ss")
    @Expose
    private long countRankSs;

    @SerializedName("count_rank_ssh")
    @Expose
    private long countRankSsh;

    @SerializedName("count_rank_s")
    @Expose
    private long countRankS;

    @SerializedName("count_rank_sh")
    @Expose
    private long countRankSh;

    @SerializedName("count_rank_a")
    @Expose
    private long countRankA;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("pp_country_rank")
    @Expose
    private long ppCountryRank;

    @SerializedName("events")
    @Expose
    private List<Event> events = null;

    @Data @Builder @AllArgsConstructor @NoArgsConstructor
    private static class Event
    {
        @SerializedName("display_html")
        @Expose
        private String displayHtml;

        @SerializedName("beatmap_id")
        @Expose
        private long beatmapId;

        @SerializedName("beatmapset_id")
        @Expose
        private long beatmapSetId;

        @SerializedName("date")
        @Expose
        private String date;

        @SerializedName("epicfactor")
        @Expose
        private int epicFactor;
    }
}
