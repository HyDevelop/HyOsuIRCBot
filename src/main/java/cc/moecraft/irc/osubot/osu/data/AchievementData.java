package cc.moecraft.irc.osubot.osu.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementData extends DataBase
{
    @SerializedName("id")
    @Expose
    public Long id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("grouping")
    @Expose
    public String grouping;

    @SerializedName("ordering")
    @Expose
    public Long ordering;

    @SerializedName("slug")
    @Expose
    public String slug;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("mode")
    @Expose
    public String mode;
}
