package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.osu.OsuAPIUtils;
import cc.moecraft.irc.osubot.osu.data.BeatmapData;
import cc.moecraft.irc.osubot.osu.data.UserScoreData;
import cc.moecraft.irc.osubot.osu.exceptions.BeatmapScoreNotEnoughException;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.RequiredParamIsNullException;
import cc.moecraft.irc.osubot.osu.parameters.BeatmapParameters;
import cc.moecraft.irc.osubot.utils.ReflectUtils;
import cc.moecraft.irc.osubot.utils.TimeUnits;
import cc.moecraft.irc.osubot.utils.TimeUtils;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/02 创建!
 * Created by Hykilpikonna on 2018/05/02!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class CommandMap extends Command
{
    public CommandMap()
    {
        super("map", "beatmap", "mapinfo");
    }

    /**
     * 查询谱面
     * 用法:
     *  !map [id]        查询谱面信息
     *
     * @param event 事件
     * @param sender 发送者的irc用户 ( 在osu的irc服务器的话用户名就是osu的用户名 )
     * @param channel 频道
     * @param command 指令名 ( 不包含指令参数 )
     * @param args 指令参数 ( 不包含指令名 )
     */
    @Override
    public void run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        if (args.size() < 1)
        {
            Main.getMessenger().respond(event,"没有输入地图ID怎样找嘛...!");
        }
        else if (args.size() > 1)
        {
            Main.getMessenger().respond(event, "哪里输错了...?");
        }
        else
        {
            int beatmapId = 0;
            try
            {
                beatmapId = Integer.parseInt(args.get(0));
            } catch (NumberFormatException e) {
                Main.getMessenger().respond(event, "输入的谱面id必须是最大32位的数字形式");
            }

            process(event, beatmapId);
        }
    }

    public static void process(GenericMessageEvent event, int beatmapId)
    {
        try
        {
            ArrayList<BeatmapData> beatmapData = Main.getWrapper().getBeatmap(BeatmapParameters.builder().b(String.valueOf(beatmapId)).build());

            BeatmapData data = beatmapData.get(0);

            // 获取Mode名字
            String modeName = OsuAPIUtils.getModeNameWithMode(data.getMode());

            // 四舍五入
            ReflectUtils.roundAllNumbers(data, 1);

            // 获取时间
            String time = TimeUtils.convertToString("m:ss", data.getHitLength(), TimeUnits.Second);

            // 获取PP
            String ppMsg;

            try {
                UserScoreData score = Main.getWrapper().getScore(data);

                ppMsg = Math.round(score.getPp() * 100d) / 100d + " PP";
            } catch (BeatmapScoreNotEnoughException e) {
                ppMsg = "无计分!";
                //TODO: 报错收集表
            }

            String format = "[osu://b/%beatmap_id% [%cm%: %artist% - %title% (%version%)]]: %ppmsg% | ⏳ %ct% | ★ %difficultyrating% | BPM %bpm% | AR %diff_approach% | CS %diff_size% | OD %diff_overall%";

            format = ReflectUtils.replaceReflectVariables(data, format, false, true);
            format = format.replace("%cm%", modeName);
            format = format.replace("%ct%", time);
            format = format.replace("%ppmsg%", ppMsg);

            Main.getMessenger().respond(event, format);

        } catch (JsonEmptyException e) {
            Main.getMessenger().respond(event, "此谱面不存在!");
        } catch (MalformedURLException | RequiredParamIsNullException | IllegalAccessException e) {
            Main.getMessenger().respond(event, "未知后台错误, 请联系me@hydev.org");
            e.printStackTrace();
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.map";
    }
}
