package cc.moecraft.irc.osubot.osu.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/02 创建!
 * Created by Hykilpikonna on 2018/05/02!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeatmapData extends DataBase
{
    @SerializedName("beatmapset_id")
    @Expose
    private String beatmapsetId;

    @SerializedName("beatmap_id")
    @Expose
    private String beatmapId;

    @SerializedName("approved")
    @Expose
    private String approved;

    @SerializedName("total_length")
    @Expose
    private String totalLength;

    @SerializedName("hit_length")
    @Expose
    private String hitLength;

    @SerializedName("version")
    @Expose
    private String version;

    @SerializedName("file_md5")
    @Expose
    private String fileMd5;

    @SerializedName("diff_size")
    @Expose
    private String diffSize;

    @SerializedName("diff_overall")
    @Expose
    private String diffOverall;

    @SerializedName("diff_approach")
    @Expose
    private String diffApproach;

    @SerializedName("diff_drain")
    @Expose
    private String diffDrain;

    @SerializedName("mode")
    @Expose
    private String mode;

    @SerializedName("approved_date")
    @Expose
    private String approvedDate;

    @SerializedName("last_update")
    @Expose
    private String lastUpdate;

    @SerializedName("artist")
    @Expose
    private String artist;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("creator")
    @Expose
    private String creator;

    @SerializedName("bpm")
    @Expose
    private String bpm;

    @SerializedName("source")
    @Expose
    private String source;

    @SerializedName("tags")
    @Expose
    private String tags;

    @SerializedName("genre_id")
    @Expose
    private String genreId;

    @SerializedName("language_id")
    @Expose
    private String languageId;

    @SerializedName("favourite_count")
    @Expose
    private String favouriteCount;

    @SerializedName("playcount")
    @Expose
    private String playcount;

    @SerializedName("passcount")
    @Expose
    private String passCount;

    @SerializedName("max_combo")
    @Expose
    private String maxCombo;

    @SerializedName("difficultyrating")
    @Expose
    private String difficultyRating;
}
