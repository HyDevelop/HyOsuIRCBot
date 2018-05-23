package cc.moecraft.irc.osubot.minigames.fingers;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.yaml.Config;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2018/05/23 创建!
 * Created by Hykilpikonna on 2018/05/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class MLFingersDatabase extends Config
{
    public MLFingersDatabase()
    {
        super(Main.PATH + File.separator + "experimental", "MLFingersDatabase", "yml", false, true);
    }

    /**
     * 设置一步的胜率信息
     * @param move 一步
     * @param winRatio 胜率信息
     */
    public void setWR(MLFingersMove move, MLFingersWinRatio winRatio)
    {
        set(move.toString(), winRatio.toString());
    }

    /**
     * 获取一步的胜率信息
     * @param move 一步
     * @return 胜率信息
     */
    public MLFingersWinRatio getWR(MLFingersMove move)
    {
        return MLFingersWinRatio.parse(getString(move.toString()));
    }

    /**
     * 一步是否存在
     * @param move 一步
     * @return 是否存在
     */
    public boolean containsWR(MLFingersMove move)
    {
        return contains(move.toString());
    }

    @Override
    public void readConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {

    }
}
