package cc.moecraft.irc.osubot.osu.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 此类由 Hykilpikonna 在 2018/05/06 创建!
 * Created by Hykilpikonna on 2018/05/06!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBestData extends DataBase implements Comparable<UserBestData>
{
    @SerializedName("beatmap_id")
    @Expose
    public Long beatmapId;

    @SerializedName("score")
    @Expose
    public Long score;

    @SerializedName("maxcombo")
    @Expose
    public int maxcombo;

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
    public int countmiss;

    @SerializedName("countkatu")
    @Expose
    public int countkatu;

    @SerializedName("countgeki")
    @Expose
    public int countgeki;

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

    @Override
    public int compareTo(UserBestData o)
    {
        try {
            Date thisDate = getDateObject();
            Date otherDate = o.getDateObject();

            return otherDate.compareTo(thisDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Date getDateObject() throws ParseException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.parse(getDate());
    }
}
