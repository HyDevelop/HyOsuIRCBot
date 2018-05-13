package cc.moecraft.irc.osubot;

import cc.moecraft.irc.osubot.utils.PropertiesUtil;
import cc.moecraft.yaml.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class BotConfig extends Config
{
    private Logger logger = LoggerFactory.getLogger(BotConfig.class);

    private static final String ircName = PropertiesUtil.readKey("irc_name");
    private static final String ircPassword = PropertiesUtil.readKey("irc_password");
    private static final String osuKey = PropertiesUtil.readKey("osu_key");

    public BotConfig()
    {
        super(Main.PATH, "Config", "yml", false, true);

        logger.info("配置文件路径: " + getConfigFile().getAbsolutePath());
    }

    /**
     * 获取用户列表
     * @return 用户列表
     */
    public ArrayList<BotAccount> getAccounts()
    {
        ArrayList<BotAccount> result = new ArrayList<>();

        for (String account : getKeys("Accounts"))
        {
            result.add(new BotAccount(
                    getString("Accounts." + account + ".Username"),
                    getString("Accounts." + account + ".Password"),
                    getBoolean("Accounts." + account + ".Channel")
            ));
        }

        return result;
    }

    /**
     * 获取配置中的管理员用户名列表
     * @return 管理员用户名列表
     */
    public ArrayList<String> getAdminUsernames()
    {
        return new ArrayList<>(getStringList("BotProperties.AdminUsernames"));
    }

    /**
     * 设置管理员用户名列表
     * @param adminUsernames 管理员用户名列表
     */
    public void setAdminUsernames(ArrayList<String> adminUsernames)
    {
        set("BotProperties.AdminUsernames", adminUsernames);
        save();
    }

    @Override
    public void readConfig() {

    }

    @Override
    public void writeDefaultConfig()
    {
        addDefault("ServerProperties.Address", "irc.ppy.sh");
        addDefault("ServerProperties.Port", 6667);

        addDefault("Accounts.default.Username", ircName);
        addDefault("Accounts.default.Password", ircPassword);
        addDefault("Accounts.default.Channel", true);
        setAdminUsernames(new ArrayList<>(Arrays.asList("Hykilpikonna", "dullwolf")));

        addDefault("BotProperties.CommandPrefix", "~");
        addDefault("BotProperties.EnabledCommandPrefixes", new String[]{"!", ";", "-", ".", "?", "*"});
        addDefault("BotProperties.AutoJoinChannels", new String[]{"#general", "#chinese"});

        addDefault("BotProperties.Download.Timeout", 3000);
        addDefault("BotProperties.Download.Osu.APIKey", osuKey); // https://github.com/ppy/osu-api/wiki

        addDefault("BotProperties.DisableChannelReply", true);
        addDefault("BotProperties.DebugLogging", false);

        addDefault("BotProperties.AntiSpam.NotACommandExcludedUsernames", new String[]{"dullwolf", "hykilpikonna", "banchobot", "Tillerino", "Ameo"});

        save();
    }
}
