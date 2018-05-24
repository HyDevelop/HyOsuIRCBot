package cc.moecraft.irc.osubot.minigames.fingers;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.yaml.Config;
import io.swagger.models.auth.In;

import java.math.BigDecimal;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class FingersDatabase extends Config
{
    public FingersDatabase()
    {
        super(Main.PATH, "FingersDatabase", "yml", false, true);
    }

    /**
     * 添加/设置一条最佳移动数据
     *
     * 记录格式例子:
     *   如果当前局面为 [3, 2] [8, 1]
     *   最佳移动为 [2, 8]
     *   胜利数为54, 失败数为27, 循环数为17, 那么会记录成这样:
     *
     *   D3281: 2/8/54/27/17
     *
     * @param move 最佳移动数据
     */
    public void setBestMoveData(FingersWinRatioData move)
    {
        set("D" + move.getBotMoveData().getData().toString(), String.format("%s/%s/%s/%s/%s",
                        move.getBotMoveData().getFromBotFinger(),
                        move.getBotMoveData().getToPlayerFinger(),
                        move.getWin(), move.getLose(), move.getDraw()));
    }

    /**
     * 获取最佳移动数据
     * @param data 当前局面
     * @return 最佳移动数据
     */
    public FingersWinRatioData getBestMoveData(FingersData data)
    {
        String[] result = getString("D" + data.toString()).split("/");

        return new FingersWinRatioData(
                new FingersBotMoveData(
                        data,
                        Integer.parseInt(result[0]),
                        Integer.parseInt(result[1])
                ),
                new BigDecimal(result[2]),
                new BigDecimal(result[3]),
                new BigDecimal(result[4])
        );
    }

    public boolean containsBestMoveData(FingersData data)
    {
        return contains("D" + data.toString());
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
