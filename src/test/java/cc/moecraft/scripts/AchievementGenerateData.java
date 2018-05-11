package cc.moecraft.scripts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/05/10 创建!
 * Created by Hykilpikonna on 2018/05/10!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data @Builder @AllArgsConstructor
public class AchievementGenerateData
{
    private String class_package;
    private String current_date_and_time;
    private String name_capitalized;
    private String id;
    private String name;
    private String grouping;
    private String ordering;
    private String slug;
    private String description;
    private String mode;
    private String tutorial;
    private String recommend;
    private String mods;
    private String completion_time;
    private String average_retry;
}
