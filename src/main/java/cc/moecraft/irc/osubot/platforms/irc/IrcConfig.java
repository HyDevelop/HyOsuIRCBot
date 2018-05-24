package cc.moecraft.irc.osubot.platforms.irc;

import cc.moecraft.irc.osubot.Main;
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
public class IrcConfig extends Config
{
    public IrcConfig()
    {
        super(Main.PATH, "IrcConfig", "yml", false, true);

        Main.getLogger().log("配置文件路径: " + getConfigFile().getAbsolutePath());
    }

    /**
     * 获取用户列表
     * @return 用户列表
     */
    public ArrayList<IrcAccount> getAccounts()
    {
        ArrayList<IrcAccount> result = new ArrayList<>();

        for (String account : getKeys("Accounts"))
        {
            result.add(new IrcAccount(
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

        addDefault("Accounts.default.Username", PropertiesUtil.readKey("irc_name"));
        addDefault("Accounts.default.Password", PropertiesUtil.readKey("irc_password"));
        addDefault("Accounts.default.Channel", true);
        setAdminUsernames(new ArrayList<>(Arrays.asList("Hykilpikonna", "dullwolf")));

        addDefault("BotProperties.CommandPrefix", "~");
        addDefault("BotProperties.EnabledCommandPrefixes", new String[]{"!", ";", "-", ".", "?"});
        addDefault("BotProperties.AutoJoinChannels", new String[]{"#general", "#chinese"});

        addDefault("BotProperties.AntiSpam.NotACommandExcludedUsernames", new String[]{"dullwolf", "Hykilpikonna", "BanchoBot", "Tillerino", "Ameo"});

        save();
    }
}
