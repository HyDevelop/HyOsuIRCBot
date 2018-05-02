package cc.moecraft.irc.osubot.osu.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/02 创建!
 * Created by Hykilpikonna on 2018/05/02!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
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
    public String beatmapsetId;

    @SerializedName("beatmap_id")
    @Expose
    public String beatmapId;

    @SerializedName("approved")
    @Expose
    public String approved;

    @SerializedName("total_length")
    @Expose
    public String totalLength;

    @SerializedName("hit_length")
    @Expose
    public String hitLength;

    @SerializedName("version")
    @Expose
    public String version;

    @SerializedName("file_md5")
    @Expose
    public String fileMd5;

    @SerializedName("diff_size")
    @Expose
    public String diffSize;

    @SerializedName("diff_overall")
    @Expose
    public String diffOverall;

    @SerializedName("diff_approach")
    @Expose
    public String diffApproach;

    @SerializedName("diff_drain")
    @Expose
    public String diffDrain;

    @SerializedName("mode")
    @Expose
    public String mode;

    @SerializedName("approved_date")
    @Expose
    public String approvedDate;

    @SerializedName("last_update")
    @Expose
    public String lastUpdate;

    @SerializedName("artist")
    @Expose
    public String artist;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("creator")
    @Expose
    public String creator;

    @SerializedName("bpm")
    @Expose
    public String bpm;

    @SerializedName("source")
    @Expose
    public String source;

    @SerializedName("tags")
    @Expose
    public String tags;

    @SerializedName("genre_id")
    @Expose
    public String genreId;

    @SerializedName("language_id")
    @Expose
    public String languageId;

    @SerializedName("favourite_count")
    @Expose
    public String favouriteCount;

    @SerializedName("playcount")
    @Expose
    public String playcount;

    @SerializedName("passcount")
    @Expose
    public String passCount;

    @SerializedName("max_combo")
    @Expose
    public String maxCombo;

    @SerializedName("difficultyrating")
    @Expose
    public String difficultyRating;
}
