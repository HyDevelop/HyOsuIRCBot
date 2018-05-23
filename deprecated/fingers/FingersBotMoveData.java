package cc.moecraft.irc.osubot.minigames.fingers;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data @AllArgsConstructor
public class FingersBotMoveData
{
    public FingersData data;
    public int fromBotFinger;
    public int toPlayerFinger;

    @Override
    public String toString()
    {
        return String.format("[%s, %s]", getFromBotFinger(), getToPlayerFinger());
    }
}
