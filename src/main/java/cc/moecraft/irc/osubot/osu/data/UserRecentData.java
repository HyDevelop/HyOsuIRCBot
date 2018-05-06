package cc.moecraft.irc.osubot.osu.data;

import cc.moecraft.irc.osubot.osu.OsuAPIWrapper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;


/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRecentData extends DataBase
{
    @SerializedName("beatmap_id")
    @Expose
    public int beatmapId;

    @SerializedName("score")
    @Expose
    public long score;

    @SerializedName("maxcombo")
    @Expose
    public int maxCombo;

    @SerializedName("count50")
    @Expose
    public int count50;

    @SerializedName("count100")
    @Expose
    public int count100;

    @SerializedName("count300")
    @Expose
    public int count300;

    @SerializedName("countmiss")
    @Expose
    public int countMiss;

    @SerializedName("countkatu")
    @Expose
    public int countKatu; // 喝

    @SerializedName("countgeki")
    @Expose
    public int countgeki; // 激

    @SerializedName("perfect")
    @Expose
    public int perfect;

    @SerializedName("enabled_mods")
    @Expose
    public int enabledMods;

    @SerializedName("user_id")
    @Expose
    public int userId;

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("rank")
    @Expose
    public String rank;

    public double getAcc(OsuAPIWrapper wrapper, int mode)
    {
        return wrapper.getAcc(mode, count300, count100, count50, countMiss, countKatu, countgeki);
    }
}
