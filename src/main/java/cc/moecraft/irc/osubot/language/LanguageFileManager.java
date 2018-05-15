package cc.moecraft.irc.osubot.language;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.yaml.Config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/04/21 创建!
 * Created by Hykilpikonna on 2018/04/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class LanguageFileManager
{
    private Map<String, LanguageFile> languageFileMap; // 语言名字对应文件
    public static final String DEFAULT_LANG = "cn";

    public LanguageFileManager()
    {
        languageFileMap = new HashMap<>();
        getFile(DEFAULT_LANG); // 创建默认语言文件
    }

    public String get(String lang, String placeholder)
    {
        return getFile(lang).get(placeholder);
    }

    public LanguageFile getFile(String lang)
    {
        lang = getLang(lang);

        if (languageFileMap.containsKey(lang))
        {
            return languageFileMap.get(lang);
        }
        else
        {
            languageFileMap.put(lang, new LanguageFile(lang));
            return getFile(lang);
        }
    }

    public String getLang(String original)
    {
        return !hasLanguageFile(original) ? DEFAULT_LANG : original;
    }

    public boolean hasLanguageFile(String lang)
    {
        return new LanguageFile(lang).exists();
    }

    public class LanguageFile extends Config
    {
        String lang;

        LanguageFile(String lang)
        {
            super(Main.PATH + File.separator + "language", "Language@" + lang, "yml", false, false);
            this.lang = lang;
            if (exists() || DEFAULT_LANG.equals(lang)) initialize();
        }

        /**
         * 判断语言文件是否存在
         * @return 是否存在
         */
        public boolean exists()
        {
            return getConfigFile().exists();
        }

        /**
         * 获取语言消息
         * @param languageNode 语言节点
         * @return 语言消息
         */
        public String get(String languageNode)
        {
            return getString(languageNode);
        }

        @Override
        public void readConfig() {

        }

        @Override
        public void writeDefaultConfig()
        {
            if (lang == null) return;

            addDefault("CommandManager_109", "UNKNOWN COMMAND: 未知指令 ( 输入%prefix%help显示帮助 )");
            addDefault("CommandRecent_61", "请输入50以下的数字");
            addDefault("CommandHelp_32", "指令列表: [http://help.bot.hydev.org 点这里(help.bot.hydev.org)]");
            addDefault("CommandStats_77", "未知后台错误, 请联系me@hydev.org");
            addDefault("CommandPush_141", "未找到用户: %info_getUsername%, 如果确定该用户存在, 请联系me@hydev.org");
            addDefault("CommandMap_65", "输入的谱面id必须是最大32位的数字形式");
            addDefault("CommandAchievement_55", "%prefix%achieve [成就名或成就ID]");
            addDefault("CommandMap_56", "哪里输错了...?");
            addDefault("CommandStats_80", "未找到用户: %usernameAndMode_getUsername%, 如果确定该用户存在, 请联系me@hydev.org");
            addDefault("CommandPing_26", "Pong!");
            addDefault("CommandMap_52", "没有输入地图ID怎样找嘛...!");
            addDefault("CommandMap_111", "此谱面不存在!");
            addDefault("CommandManager_76", "NO PERM: 无法执行%prefix%%commandArgs_getCommandName%, 因为缺少权限");
            addDefault("CommandPush_75", "请输入用户名... 你不会真的想推荐给自己吧...");
            addDefault("CommandManager_99", "NOT A COMMAND: 不是指令 ( 输入%prefix%help显示帮助 )");
            addDefault("CommandInfo_34", "浅黄：普通用户｜黄色：[https://osu.ppy.sh/p/support 捐赠玩家]｜红色：[https://osu.ppy.sh/wiki/QAT QAT（谱面质量保证团队）] 或是 [https://osu.ppy.sh/help/wiki/People/Global_Moderation_Team GMT（社群管理团队）]｜白色：你自己，或是一行使用 /me 指令的动作讯息｜深蓝色：私信｜绿色：有人提到你的名字（或是 Highlight）｜粉红色：IRC 萌萌机器人（ｂａｎｃｈｏｂｏｔ）");
            addDefault("CommandUpdate_68", "欢迎新大佬使用HyIRC机器人! 这个指令是Ameo的[https://ameobea.me/osutrack/ Osu!Track]统计功能!");
            addDefault("CommandUpdate_69", "这个指令的数值代表着从上次输入指令到这次输入指令之间的进步!");
        }

        // 所有获取/写入的语言节点全用小写

        @Override
        public void addDefault(String path, Object value)
        {
            super.addDefault(path.toLowerCase(), value);
        }

        @Override
        public String getString(String path)
        {
            return super.getString(path.toLowerCase());
        }
    }
}
