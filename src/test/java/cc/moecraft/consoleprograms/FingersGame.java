package cc.moecraft.consoleprograms;

import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersAI;
import cc.moecraft.irc.osubot.command.commands.fun.minigames.fingers.FingersData;
import cc.moecraft.logger.DebugLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

/**
 * 此类由 Hykilpikonna 在 2018/05/22 创建!
 * Created by Hykilpikonna on 2018/05/22!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class FingersGame
{
    private static DebugLogger logger = new DebugLogger("FingersGame", true);

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while (true)
        {
            logger.log("请输入局面数据 (1111): ");

            String[] in = reader.readLine().split("");

            logger.log(Arrays.toString(FingersAI.calculateBestMove(new FingersData(Integer.parseInt(in[0]), Integer.parseInt(in[1]), Integer.parseInt(in[2]), Integer.parseInt(in[3])))));
        }
    }
}
