package cc.moecraft.irc.osubot.minigames.fingers.exceptions;

import cc.moecraft.irc.osubot.minigames.fingers.FingersPlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 此类由 Hykilpikonna 在 2018/05/23 创建!
 * Created by Hykilpikonna on 2018/05/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@Data @AllArgsConstructor
public class NotYourTurnException extends Exception
{
    public FingersPlayerType whoIsRequested;
    public FingersPlayerType whoItShouldBe;
}
