package cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data @AllArgsConstructor
public class FingersWinRatioData
{
    public FingersBotMoveData botMoveData;
    public int win;
    public int lose;
    public int draw;
}
