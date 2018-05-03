package cc.moecraft.irc.osubot.osu.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

/**
 * 此类由 Hykilpikonna 的 JSONClassGenerator 生成!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
@EqualsAndHashCode(callSuper = true)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class OsuTrackData extends DataBase
{
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("mode")
    @Expose
    private Long mode;

    @SerializedName("playcount")
    @Expose
    private Long playcount;

    @SerializedName("pp_rank")
    @Expose
    private Long ppRank;

    @SerializedName("pp_raw")
    @Expose
    private Double ppRaw;

    @SerializedName("accuracy")
    @Expose
    private Double accuracy;

    @SerializedName("total_score")
    @Expose
    private Long totalScore;

    @SerializedName("ranked_score")
    @Expose
    private Long rankedScore;

    @SerializedName("count300")
    @Expose
    private Long count300;

    @SerializedName("count50")
    @Expose
    private Long count50;

    @SerializedName("count100")
    @Expose
    private Long count100;

    @SerializedName("level")
    @Expose
    private Double level;

    @SerializedName("count_rank_a")
    @Expose
    private Long countRankA;

    @SerializedName("count_rank_s")
    @Expose
    private Long countRankS;

    @SerializedName("count_rank_ss")
    @Expose
    private Long countRankSs;

    @SerializedName("levelup")
    @Expose
    private Boolean levelup;

    @SerializedName("first")
    @Expose
    private Boolean first;

    @SerializedName("exists")
    @Expose
    private Boolean exists;

    @SerializedName("newhs")
    @Expose
    private List<NewH> newHs = null;

    @Data @AllArgsConstructor @NoArgsConstructor @Builder
    private static class NewH
    {
        @SerializedName("beatmap_id")
        @Expose
        private String beatmapId;

        @SerializedName("score")
        @Expose
        private String score;

        @SerializedName("maxcombo")
        @Expose
        private String maxcombo;

        @SerializedName("count50")
        @Expose
        private String count50;

        @SerializedName("count100")
        @Expose
        private String count100;

        @SerializedName("count300")
        @Expose
        private String count300;

        @SerializedName("countmiss")
        @Expose
        private String countmiss;

        @SerializedName("countkatu")
        @Expose
        private String countkatu;

        @SerializedName("countgeki")
        @Expose
        private String countgeki;

        @SerializedName("perfect")
        @Expose
        private String perfect;

        @SerializedName("enabled_mods")
        @Expose
        private String enabledMods;

        @SerializedName("user_id")
        @Expose
        private String userId;

        @SerializedName("date")
        @Expose
        private String date;

        @SerializedName("rank")
        @Expose
        private String rank;

        @SerializedName("pp")
        @Expose
        private String pp;

        @SerializedName("ranking")
        @Expose
        private Long ranking;
    }
}
