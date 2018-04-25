package cc.moecraft.irc.osubot.osu.parameters.tags;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 此类由 Hykilpikonna 在 2018/04/24 创建!
 * Created by Hykilpikonna on 2018/04/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpParameter
{
    boolean required();
}
