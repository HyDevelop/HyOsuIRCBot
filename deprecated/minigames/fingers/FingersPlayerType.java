package cc.moecraft.irc.osubot.minigames.fingers;

/**
 * 此类由 Hykilpikonna 在 2018/05/23 创建!
 * Created by Hykilpikonna on 2018/05/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public enum FingersPlayerType
{
    Player, Bot;

    /**
     * 获取另外一个
     * @return 如果是玩家就返回Bot, 如果是Bot就返回玩家
     */
    public FingersPlayerType getTheOther()
    {
        if (this == Player) return Bot;
        return Player;
    }
}
