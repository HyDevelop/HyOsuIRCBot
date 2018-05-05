package cc.moecraft.irc.osubot.osu.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor @Data
public class RecentScoreNotEnough extends Exception
{
    int limit;
    int requested;
    int mode;
}
