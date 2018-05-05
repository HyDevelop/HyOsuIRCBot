package cc.moecraft.irc.osubot.osu.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserScoreData extends DataBase
{
    @SerializedName("score_id")
    @Expose
    public Long scoreId;

    @SerializedName("score")
    @Expose
    public Long score;

    @SerializedName("username")
    @Expose
    public String username;

    @SerializedName("maxcombo")
    @Expose
    public Long maxcombo;

    @SerializedName("count50")
    @Expose
    public Long count50;

    @SerializedName("count100")
    @Expose
    public Long count100;

    @SerializedName("count300")
    @Expose
    public Long count300;

    @SerializedName("countmiss")
    @Expose
    public Long countmiss;

    @SerializedName("countkatu")
    @Expose
    public Long countkatu;

    @SerializedName("countgeki")
    @Expose
    public Long countgeki;

    @SerializedName("perfect")
    @Expose
    public Long perfect;

    @SerializedName("enabled_mods")
    @Expose
    public Long enabledMods;

    @SerializedName("user_id")
    @Expose
    public Long userId;

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("rank")
    @Expose
    public String rank;

    @SerializedName("pp")
    @Expose
    public Double pp;

    @SerializedName("replay_available")
    @Expose
    public Long replayAvailable;
}
