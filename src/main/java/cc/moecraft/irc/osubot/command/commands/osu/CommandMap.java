package cc.moecraft.irc.osubot.command.commands.osu;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.command.Command;
import cc.moecraft.irc.osubot.language.MultiLanguageText;
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
 * QQ: admin@moecraft.cc -OR- 871674895
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
    public MultiLanguageText run(GenericMessageEvent event, User sender, Channel channel, String command, ArrayList<String> args)
    {
        if (args.size() < 1)
        {
            return MultiLanguageText.languageNode("commands.osu.map_error_no_args");
        }
        else if (args.size() > 1)
        {
            return MultiLanguageText.languageNode("commands.osu.map_error_args_size");
        }
        else
        {
            int beatmapId;
            try
            {
                beatmapId = Integer.parseInt(args.get(0));
            }
            catch (NumberFormatException e)
            {
                return MultiLanguageText.languageNode("commands.osu.map_error_not_int");
            }

            return process(event, beatmapId);
        }
    }

    public static MultiLanguageText process(GenericMessageEvent event, int beatmapId)
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

            try
            {
                UserScoreData score = Main.getWrapper().getScore(data);

                ppMsg = Math.round(score.getPp() * 100d) / 100d + " PP";
            }
            catch (BeatmapScoreNotEnoughException e)
            {
                ppMsg = Main.getMessenger().getText(event, MultiLanguageText.languageNode("keywords.unranked"));
            }

            return MultiLanguageText.languageNode("commands.osu.map_format")
                    .putVariables(data, true)
                    .putVariable("cm", modeName)
                    .putVariable("ct", time)
                    .putVariable("ppmsg", ppMsg);
        }
        catch (JsonEmptyException e)
        {
            return MultiLanguageText.languageNode("commands.osu.map_error_unknown_map");
        }
        catch (MalformedURLException | RequiredParamIsNullException | IllegalAccessException e)
        {
            e.printStackTrace();
            return MultiLanguageText.languageNode("errors.unknown_backend_error");
        }
    }

    @Override
    public String permissionRequired()
    {
        return "irc.user.regular.osu.map";
    }
}
