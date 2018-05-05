package cc.moecraft.irc.osubot.osu.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.shardingjdbc.core.routing.type.all.DatabaseAllRoutingEngine;
import lombok.*;


/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
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
    public int count100p; // 喝

    @SerializedName("countgeki")
    @Expose
    public int count300p; // 激

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

    public double getAcc(int mode)
    {
        double acc = 0;
        switch (mode)
        {
            case 0:
                acc = (count300 * 300.0 + count100 * 100.0 + count50 * 50.0) / ((count300 + count100 + count50 + countMiss) * 300);
                break;
            case 1:
                acc = (count300 + count100 * 0.5) / (count300 + count100 + countMiss);
                break;
            case 2:
                int base = count300 + count100 + count50;
                acc = (double) base / (base + (countMiss + count100p));
                break;
            case 3:
                acc = (double)(count50 * 50 + count100 * 100 + count100p * 200 + (count300 + count300p) * 300) / ((count300 + count100 + count50 + countMiss + count100p + count300p) * 300);
                break;
            default:
                break;
        }

        return acc;
    }
}
