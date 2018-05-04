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
    private int beatmapsetId;

    @SerializedName("beatmap_id")
    @Expose
    private int beatmapId;

    @SerializedName("approved")
    @Expose
    private int approved;

    @SerializedName("total_length")
    @Expose
    private int totalLength;

    @SerializedName("hit_length")
    @Expose
    private int hitLength;

    @SerializedName("version")
    @Expose
    private String version;

    @SerializedName("file_md5")
    @Expose
    private String fileMd5;

    @SerializedName("diff_size")
    @Expose
    private int diffSize;

    @SerializedName("diff_overall")
    @Expose
    private int diffOverall;

    @SerializedName("diff_approach")
    @Expose
    private int diffApproach;

    @SerializedName("diff_drain")
    @Expose
    private int diffDrain;

    @SerializedName("mode")
    @Expose
    private int mode;

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
    private int bpm;

    @SerializedName("source")
    @Expose
    private String source;

    @SerializedName("tags")
    @Expose
    private String tags;

    @SerializedName("genre_id")
    @Expose
    private int genreId;

    @SerializedName("language_id")
    @Expose
    private int languageId;

    @SerializedName("favourite_count")
    @Expose
    private int favouriteCount;

    @SerializedName("playcount")
    @Expose
    private int playcount;

    @SerializedName("passcount")
    @Expose
    private int passCount;

    @SerializedName("max_combo")
    @Expose
    private int maxCombo;

    @SerializedName("difficultyrating")
    @Expose
    private double difficultyRating;
}
