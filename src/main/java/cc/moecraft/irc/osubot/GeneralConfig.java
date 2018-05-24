package cc.moecraft.irc.osubot;

import cc.moecraft.irc.osubot.platforms.irc.IrcAccount;
import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import cc.moecraft.yaml.Config;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class GeneralConfig extends Config
{
    public GeneralConfig()
    {
        super(Main.PATH, "Config", "yml", false, true);

        Main.getLogger().log("配置文件路径: " + getConfigFile().getAbsolutePath());
    }

    @Override
    public void readConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        addDefault("BotProperties.Download.Timeout", 3000);
        addDefault("BotProperties.Download.Osu.APIKey", PropertiesUtil.readKey("osu_key")); // https://github.com/ppy/osu-api/wiki

        addDefault("BotProperties.DisableChannelReply", true);
        addDefault("BotProperties.DebugLogging", false);

        save();
    }
}
