package cc.moecraft.irc.osubot.minigames.fingers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GameEndedException extends Exception
{
    private WinnerType winner;

    public enum WinnerType
    {
        PLAYER, BOT
    }
}
