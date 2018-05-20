package cc.moecraft.irc.osubot.language;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.yaml.Config;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    public String get(String lang, String languageNode)
    {
        return getFile(lang).get(languageNode);
    }

    /**
     * 随机获取一条消息
     * @param lang 语言
     * @param languageNode 语言节点
     * @return List里的随机一条消息
     */
    public String getRandomInAList(String lang, String languageNode)
    {
        List<String> list = getFile(lang).getStringList(languageNode.toLowerCase());
        return list.get(new Random().nextInt(list.size()));
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

            addDefault("manager.unknown_command", "未知指令 ( 输入%prefix%help显示帮助 )");
            addDefault("manager.no_permission", "无法执行%prefix%%command%, 因为缺少权限");
            addDefault("manager.not_command", "不是指令 ( 输入%prefix%help显示帮助 )");
            addDefault("errors.unknown_backend_error", "未知后台错误, 请联系me@hydev.org");
            addDefault("errors.unknown_username", "未找到用户: %username%, 如果确定该用户存在, 请联系me@hydev.org");
            addDefault("errors.scores_not_enough", "现在你%mode%模式的近期成绩只有%max%个... 无法获取第%current%个, 多玩玩再来看看吧!");
            addDefault("commands.general.help_text", "指令列表: [http://help.bot.hydev.org 点这里(help.bot.hydev.org)]");
            addDefault("commands.fun.ping_text", "Pong!");
            addDefault("commands.fun.time_text", "当前时间: %time%");
            addDefault("commands.fun.info_color_change", "浅黄：普通用户｜黄色：[https://osu.ppy.sh/p/support 捐赠玩家]｜红色：[https://osu.ppy.sh/wiki/QAT QAT（谱面质量保证团队）] 或是 [https://osu.ppy.sh/help/wiki/People/Global_Moderation_Team GMT（社群管理团队）]｜白色：你自己，或是一行使用 /me 指令的动作讯息｜深蓝色：私信｜绿色：有人提到你的名字（或是 Highlight）｜粉红色：IRC 萌萌机器人（ｂａｎｃｈｏｂｏｔ）");
            addDefault("commands.osu.map_error_not_int", "输入的谱面id必须是最大32位的数字形式");
            addDefault("commands.osu.map_error_args_size", "哪里输错了...?");
            addDefault("commands.osu.map_error_no_args", "没有输入地图ID怎样找嘛...!");
            addDefault("commands.osu.map_error_unknown_map", "此谱面不存在!");
            addDefault("commands.osu.map_format", "[osu://b/%beatmap_id% [%cm%: %artist% - %title% (%version%)]]: %ppmsg% | ⏳ %ct% | ★ %difficultyrating% | BPM %bpm% | AR %diff_approach% | CS %diff_size% | OD %diff_overall%");
            addDefault("commands.osu.recent_format", "[osu://b/%beatmap_id% [%cm%: %artist% - %title% (%version%)]]: ★ %difficultyrating% | 成绩: %rank% | %ppmsg% | %ca%% | %cscore% | %maxcombo%x/%max_combo%x 连击");
            addDefault("commands.osu.stats_format", "[%mode% - %username% (%user_id%)]: %pp_raw%pp | lv.%level% | #%pp_rank% | %accuracy%% acc. | %count_rank_ss%ss | %count_rank_s%s |  %count_rank_a%a ");
            addDefault("commands.osu.update_format", "[%cm% - [%clink% %username%]]: %pp_raw% pp | %level% lvl | %crank% rank | %accuracy%% acc. | %playcount% 次游戏");
            addDefault("commands.osu.update_prefix_0", new String[]{"多玩玩再来看吧! "});
            addDefault("commands.osu.update_prefix_L20", new String[]{"进步了.. 加油! "});
            addDefault("commands.osu.update_prefix_H20", new String[]{"w.. 大..大佬! "});
            addDefault("commands.osu.achievement_help", "%prefix%achieve [成就名或成就ID]");
            addDefault("commands.osu.push_format_channel", "%username%推荐给你们了刚刚在玩的谱面: [osu://b/%beatmap_id% [%cm%: %title% - %artist% (%version%)]]: %ppmsg% | ⏳ %ct% | ★ %difficultyrating% | BPM %bpm%");
            addDefault("commands.osu.push_format_user", "%username%推荐给你了刚刚在玩的谱面: [osu://b/%beatmap_id% [%cm%: %title% - %artist% (%version%)]]: %ppmsg% | ⏳ %ct% | ★ %difficultyrating% | BPM %bpm%");
            addDefault("commands.osu.push_error_input_username", "请输入用户名... 你不会真的想推荐给自己吧...");
            addDefault("commands.osu.recent_error_greater_than_50", "请输入50以下的数字");
            addDefault("commands.osu.achievement", "成就%id%: %name% - ");
            addDefault("keywords.unranked", "无计分!");
            addDefault("update_message_newbie_1", "欢迎新大佬使用HyIRC机器人! 这个指令是Ameo的[https://ameobea.me/osutrack/ Osu!Track]统计功能!");
            addDefault("update_message_newbie_2", "这个指令的数值代表着从上次输入指令到这次输入指令之间的进步!");

            // 成就 ( 默认英文 )
            addDefault("achievement.youcanthide", " Full combo a 4+ star map with HD+FL mods. [https://osu.ppy.sh/s/141 FAIRY FORE - Vivid] [Insane] unlocks this achievement");
            addDefault("achievement.tothecore", " Pass any NIGHTCORE map with DT/NC mod. If you don't have any nightcore maps [https://osu.ppy.sh/b/89547&m=0  here's one].");
            addDefault("achievement.4000000keys", "无教程信息");
            addDefault("achievement.eclipse", " Pass any map with FL+HD mods. [https://osu.ppy.sh/b/34529 Silver Forest - Marisa Spark] is a short map.");
            addDefault("achievement.february2018spotlight", "无教程信息");
            addDefault("achievement.buildingsteam", " Full combo a 3 star map without using EZ/NF/HT mods (don't miss slider ends). [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (taiko) ");
            addDefault("achievement.dekasight", " Full combo a 3+ star map with EZ+HD+FL mods. [https://osu.ppy.sh/s/141 FAIRY FORE - Vivid] is only 13 seconds long.");
            addDefault("achievement.rockaroundtheclock", " Pass any map with HR mod. Can use other mods in the same play.");
            addDefault("achievement.constellationprize", " Pass a 2 star map without using EZ/NF/HT mods.");
            addDefault("achievement.thegodfather", " [https://osu.ppy.sh/b/104229 Team Nekokan - Can't Defeat Airman] [https://osu.ppy.sh/b/736215&m=0 Panda Eyes & Teminite - Highscore] [https://osu.ppy.sh/b/901854 Sendan Life] [https://osu.ppy.sh/b/95733 rog-unlimitation [Insane]] +DT (taiko)");
            addDefault("achievement.hyperspeed", " Pass a 4 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (osu!standard) [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane]+DT (taiko)");
            addDefault("achievement.jackofalltrades", " Exact solution not known. Get 5000+ playcount in all game modes (osu!standard/taiko/mania/catch the beat) ");
            addDefault("achievement.levelbreaker", " Full combo a 6 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.businessasusual", " Full combo a 2 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.challengeaccepted", " Pass an approved map. [https://osu.ppy.sh/p/beatmaplist?m=-1&r=6&g=0&la=0&ra= LIST<a>. ");
            addDefault("achievement.timedilation", " Pass a map that is 10 minutes or longer with DT mod (check map time AFTER you put DT on). [https://osu.ppy.sh/b/9007 Yoko Ishida - paraparaMAX I] easy but long [https://osu.ppy.sh/s/8338 Lucky Star no Minna - Kumikyoku 'Lucky Star Douga'] medium [https://osu.ppy.sh/b/247037 Lucky Star no Minna - Kumikyoku 'Lucky Star Douga'] hard but short");
            addDefault("achievement.spooked", " Pass any map with FL mod and get a D rank. [https://osu.ppy.sh/b/857602&m=0 toby fox - Quiet Water [Still]] [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid [Insane]]");
            addDefault("achievement.40000keys", "无教程信息");
            addDefault("achievement.750combo", " Get 750 combo on any difficulty ranked map, osu!standard mode only!");
            addDefault("achievement.bigdrums", " Full combo a 3 star map without using EZ/NF/HT mods (don't miss slider ends). [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (taiko) ");
            addDefault("achievement.approachingthesummit", "无教程信息");
            addDefault("achievement.march2018spotlight", "无教程信息");
            addDefault("achievement.rhythmscall", " Full combo a 6 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.september2017spotlight", " Clear all maps in September 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 2 maps in osu!catch mode to unlock achievement: [https://osu.ppy.sh/s/415827 1] [https://osu.ppy.sh/s/552854 2]");
            addDefault("achievement.catch200000fruits", "无教程信息");
            addDefault("achievement.july2017spotlight", " Clear all maps in July 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 3 maps in osu!mania mode to unlock achievement: [https://osu.ppy.sh/s/207525 1] [https://osu.ppy.sh/s/426638 2] [https://osu.ppy.sh/s/459950 3]");
            addDefault("achievement.areyouafraidofthedark", " Pass any map with FL mod. Can use other mods in the same play.");
            addDefault("achievement.tunnelvision", " Pass a map with FL mod without getting more than 200 combo. NF+FL works.");
            addDefault("achievement.3000000drumhits", "无教程信息");
            addDefault("achievement.anguishquelled", " Full combo a 6 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.equilibrium", " Get an equal amount of 300's 100's 50's. Use of NF+HT is highly recommended. Minimum amount required is 15 of each type (15 300's, 15 100's, 15 50's) and misses do not count, so you can have less than 50% acc.  [https://osu.ppy.sh/b/188110&m=0 Chata - Papapapanda] [Easy]   In the map above you need to hit 1 full slider (300), ALL circles (300), 30 sliders (100), 30 sliders (50). Hitting 50's is very easy so it's recommended that you start hitting 100's on sliders first. [https://www.youtube.com/watch?v=eDM3ggDyogI&feature=youtu.be VIDEO OF ACHIEVEMENT]   ");
            addDefault("achievement.feeltheburn", "无教程信息");
            addDefault("achievement.dashingscarlet", " Full combo an 8 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.supersonic", " Full combo a 7 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.stepup", " Full combo a 7 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.keepingtime", " Full combo a 1 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.totality", " Full combo a 1 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.august2017spotlight", " Clear all maps in August 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 3 maps in osu!mania mode to unlock achievement: [https://osu.ppy.sh/s/300685 1] [https://osu.ppy.sh/s/453386 2] [https://osu.ppy.sh/s/613792 3]");
            addDefault("achievement.ameganekkoapproaches", " Meet Maria, the osu!mania mascot. Finish an osu!mania map with at least a 100 combo. Download [https://osu.ppy.sh/b/741477 this map] and play it with EZHT mods on ultra beginner difficulty. First 2 sliders should give you over 100 combo.");
            addDefault("achievement.thedrummersthrone", " Full combo an 8 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.mostimproved", " On a mapset you never played before get a D rank with at least 100,000 score on first try and then S on the second try. ");
            addDefault("achievement.maniac", " [https://osu.ppy.sh/b/111680 xi - Ascension to Heaven] +DT [https://osu.ppy.sh/b/39825 IOSYS - Marisa wa Taihen na Kanbu de Tomatte Ikimashita] +DT [https://osu.ppy.sh/s/418826 DystopiaGround - AugoEidEs] +HR (osu!catch) [https://osu.ppy.sh/b/553820 MiddleIsland - Achromat] +DT (osu!mania)");
            addDefault("achievement.animepackvol3", "无教程信息");
            addDefault("achievement.animepackvol2", "无教程信息");
            addDefault("achievement.15000plays", "无教程信息");
            addDefault("achievement.keystruck", " Full combo a 1 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.sranker", " Get five S ranks (or SS) in 24 hours ");
            addDefault("achievement.animepackvol4", "无教程信息");
            addDefault("achievement.dontletthebunnydistractyou", " Get 371 combo on Normal difficulty or 447 combo on Hard difficulty [https://osu.ppy.sh/b/8707 Chatmonchy - Make Up! Make Up!] EZ mod is allowed and may help a lot.");
            addDefault("achievement.animepackvol1", "无教程信息");
            addDefault("achievement.january2018spotlight", "无教程信息");
            addDefault("achievement.januaryfebruary2017spotlight", " Clear all maps in January/February 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play.");
            addDefault("achievement.hyperflow", " Full combo a 3 star map without using EZ/NF/HT mods (don't miss slider ends). [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (taiko) ");
            addDefault("achievement.catch20000fruits", "无教程信息");
            addDefault("achievement.jackpot", " Get 222,222 (or 6,666,666 etc.) score on any ranked map.");
            addDefault("achievement.recklessabandon", " Full combo a 3+ star map with SD+HR mods.  [https://osu.ppy.sh/b/97399&m=0 W.T. Orchestra - William Tell Overture[AngelHoney]]");
            addDefault("achievement.noteventrying", " Pass a 3 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (taiko)");
            addDefault("achievement.afterimage", " Pass any map with HT+HD mods. [https://osu.ppy.sh/b/259 TRF - Survival dAnce ~no no cry more~] is only 17 seconds for fast achievement.");
            addDefault("achievement.impulsedrive", " Pass a 3 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (taiko)");
            addDefault("achievement.paradigmshift", " Full combo a 5 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.keyingin", " Full combo a 2 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.thesumofallfears", " Full combo but miss the last note.   [https://osu.ppy.sh/b/34527 Silver Forest - Marisa Spark] [Easy] [https://osu.ppy.sh/b/7960 Cold Play - Clocks] [Easy]");
            addDefault("achievement.may2017spotlight", " Clear all maps in May 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 3 maps in osu!catch mode to unlock achievement: [https://osu.ppy.sh/s/429184 1] [https://osu.ppy.sh/s/536749 2] [https://osu.ppy.sh/s/594326 3]");
            addDefault("achievement.meticulous", " Pass a 3+ star map with EZ+PF mods. [https://osu.ppy.sh/b/932223&m=0 Mike Greene - Bill Nye the Science Guy Theme Song (Chinese Intro)] [https://osu.ppy.sh/b/452185 Disasterpeace - Jolly Frolic] [https://osu.ppy.sh/s/2086 Sonic X Intro]");
            addDefault("achievement.sweetraveparty", " Pass any map with NC mod. Can use other mods in the same play.");
            addDefault("achievement.adversityovercome", " Full combo a 4 star map without using EZ/NF/HT mods (don't miss slider ends). [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane]+DT (taiko)");
            addDefault("achievement.dashingeverforward", " Pass a 2 star map without using EZ/NF/HT mods.");
            addDefault("achievement.icanseethetop", "无教程信息");
            addDefault("achievement.drumbreaker", " Pass a 6 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid [Insane]] +DT  [https://osu.ppy.sh/b/19990 Silver Forest - Tsurupettan] +DT  [https://osu.ppy.sh/b/296 Wizards In Winter [Impossible]] +DT (taiko) ");
            addDefault("achievement.timeandahalf", " Pass any map with DT mod. Can use other mods in the same play.");
            addDefault("achievement.hyperdashon", " Pass a 4 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (osu!standard) [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane]+DT (taiko)");
            addDefault("achievement.50000plays", "无教程信息");
            addDefault("achievement.infinitesimal", " Full combo a map with CS7.8 or higher. Use HR mod to increase Circle Size. You can also use HT mod for easier achievement. [https://osu.ppy.sh/b/20382 Bill Nye the Science Guy][Hard] +HR [https://osu.ppy.sh/b/860486&m=0 toby fox - Quiet Water] [TheOnlyLeon's Flowing] +HR");
            addDefault("achievement.thegradualrise", "无教程信息");
            addDefault("achievement.addicted", " Full combo a 5 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.buildingconfidence", " Pass a 3 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (taiko)");
            addDefault("achievement.asliceoflife", " Pass a 1 star map without using EZ/NF/HT mods.");
            addDefault("achievement.beholdnodeception", " Full combo a 4+ star map with EZ mod. [https://osu.ppy.sh/s/141 FAIRY FORE - Vivid]");
            addDefault("achievement.realtorextraordinaire", " Full combo [https://osu.ppy.sh/b/792693 cYsmix - House With Legs] with HDDTHR mods (any difficulty).");
            addDefault("achievement.supremacy", " [https://osu.ppy.sh/b/104229 Team Nekokan - Can't Defeat Airman] [https://osu.ppy.sh/b/736215&m=0 Panda Eyes & Teminite - Highscore] [https://osu.ppy.sh/b/901854 Sendan Life] [https://osu.ppy.sh/b/95733 rog-unlimitation [Insane]] +DT (taiko)");
            addDefault("achievement.perseverance", " Pass any marathon map (probably 7+ minutes long). Do not use EZ/NF/HT/SO mods. [https://osu.ppy.sh/s/97433 Traktion - The Near Distant Future] unlocks this achievement [https://osu.ppy.sh/s/2916 Hyadain - Megaman Mix] (not tested)");
            addDefault("achievement.fruitninja", " Pass a 6 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid [Insane]] +DT  [https://osu.ppy.sh/b/19990 Silver Forest - Tsurupettan] +DT  [https://osu.ppy.sh/b/296 Wizards In Winter [Impossible]] +DT (taiko) ");
            addDefault("achievement.riskaverse", " Pass any map with NF mod. Can use other mods in the same play.");
            addDefault("achievement.faceyourdemons", " Pass a 4 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (osu!standard) [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane]+DT (taiko)");
            addDefault("achievement.consolationprize", " Exact solution not known. Pass any difficulty of any ranked map with a D rank and more than 100,000 score without using NF/HT/SO mods (EZ mod is allowed).  Recommended low HP drain maps: [https://osu.ppy.sh/s/21323 BoA - soundscape<a> [Hard] [https://osu.ppy.sh/b/1147&m=0 IOSYS - Marisa wa Taihen na Mono wo Nusunde Ikimashita<a> [Hard]  Some screenshots: [http://haitai.jp/img/prize1.jpg http://haitai.jp/img/prize1.jpg] [http://haitai.jp/img/prize2.jpg http://haitai.jp/img/prize2.jpg] [http://haitai.jp/img/prize3.jpg http://haitai.jp/img/prize3.jpg] [http://haitai.jp/img/prize4.jpg http://haitai.jp/img/prize4.jpg] [http://haitai.jp/img/prize5.jpg http://haitai.jp/img/prize5.jpg]");
            addDefault("achievement.absolution", " [https://osu.ppy.sh/b/111680 xi - Ascension to Heaven] +DT [https://osu.ppy.sh/b/39825 IOSYS - Marisa wa Taihen na Kanbu de Tomatte Ikimashita] +DT [https://osu.ppy.sh/s/418826 DystopiaGround - AugoEidEs] +HR (osu!catch) [https://osu.ppy.sh/b/553820 MiddleIsland - Achromat] +DT (osu!mania)");
            addDefault("achievement.rhythmincarnate", " [https://osu.ppy.sh/b/111680 xi - Ascension to Heaven] +DT [https://osu.ppy.sh/b/39825 IOSYS - Marisa wa Taihen na Kanbu de Tomatte Ikimashita] +DT [https://osu.ppy.sh/s/418826 DystopiaGround - AugoEidEs] +HR (osu!catch) [https://osu.ppy.sh/b/553820 MiddleIsland - Achromat] +DT (osu!mania)");
            addDefault("achievement.demonslayer", " Full combo a 5 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.itsrainingfruit", " Pass a 5 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/95733 07th Expansion - rog-unlimitation [Insane]] (taiko)");
            addDefault("achievement.camerashy", " Get a play with NF+HD that WOULD have been in the top 500 leaderboard if not for the score reduction of NF. Just download any [https://osu.ppy.sh/p/beatmaplist&s=4&r=0 new map] and full combo any difficulty with NF+HD mods.");
            addDefault("achievement.thrillofthechase", " Full combo [https://osu.ppy.sh/b/1043382 cYsmix - Classic Pursuit [Advanced]<a> with DT mod");
            addDefault("achievement.reachingthecore", " Full combo a 2 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.2000combo", "无教程信息");
            addDefault("achievement.justonesecond", " Pass AR9 map with HD+FL mods. [https://osu.ppy.sh/b/10848 ESTi feat. Various Artists - Zero Fill Love] [Insane+]");
            addDefault("achievement.november2017spotlight", " Clear all maps in November 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 2 maps in osu!mania mode to unlock achievement: [https://osu.ppy.sh/s/555681 1] [https://osu.ppy.sh/s/580273 2]");
            addDefault("achievement.firststeps", " Pass a 1 star map without using EZ/NF/HT mods.");
            addDefault("achievement.theseclarionskies", " Pass a 5 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/95733 07th Expansion - rog-unlimitation [Insane]] (taiko)");
            addDefault("achievement.dreamcatcher", " [https://osu.ppy.sh/b/104229 Team Nekokan - Can't Defeat Airman] [https://osu.ppy.sh/b/736215&m=0 Panda Eyes & Teminite - Highscore] [https://osu.ppy.sh/b/901854 Sendan Life] [https://osu.ppy.sh/b/95733 rog-unlimitation [Insane]] +DT (taiko)");
            addDefault("achievement.elite", " Get 1337 combo (break combo after getting it). Not all maps work. [https://osu.ppy.sh/b/651744 supercell - Kimi no Shiranai Monogatari] unlocks this achievement. Miss the first reverse slider (3 combo lost in total), get 1337 combo and miss note 6 as shown in [https://www.youtube.com/watch?v=ciATKv5Qfbs this video] You should have 1211 combo on your first map break. If you don't - you missed a slider end.");
            addDefault("achievement.5050", " Get 50 50's on any map. Get 100% accuracy [https://osu.ppy.sh/b/34529&m=2 Silver Forest - Marisa Spark [Normal]] in osu!catch mode. [https://osu.ppy.sh/b/21184 Rhapsody - Emerald Sword [Light]]. Start but don't complete sliders to get guaranteed 50 points.  NF/HT mods do not work.");
            addDefault("achievement.motoloid", "无教程信息");
            addDefault("achievement.500combo", " Get 500 combo on any difficulty ranked map, osu!standard mode only!");
            addDefault("achievement.nonormalplayer", " Pass a 2 star map without using EZ/NF/HT mods.");
            addDefault("achievement.burnedout", " Pass any map with SO mod. Can use other mods in the same play.");
            addDefault("achievement.overconfident", " Pass a map with very low accuracy using DT or HR mod. Worked with 33.33% on [https://osu.ppy.sh/b/417503 Lena Park - Inori~You Raise Me Up (TV Size)] [Lanturn's Beginner]");
            addDefault("achievement.everonwards", " Pass a 5 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/95733 07th Expansion - rog-unlimitation [Insane]] (taiko)");
            addDefault("achievement.toyourownbeat", " Full combo a 2 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.feelinit", " Your max combo must be the same as maps BPM (or half of it) Full combo [https://osu.ppy.sh/b/1267643\" >Harumachi Clover [Kisses' Easy]]");
            addDefault("achievement.upforthechallenge", " Full combo a map with AR10, OD10, HP10. Use HR mod to increase map difficulty. [https://osu.ppy.sh/b/55432 Junichi Masuda - Biking Theme (HG/SS) [Victory Road]] +HR");
            addDefault("achievement.lightsout", " Pass any map with NC+FL mods (double click on DT mod to get NC).");
            addDefault("achievement.5000plays", "无教程信息");
            addDefault("achievement.toofasttoofurious", " Pass any difficulty on [https://osu.ppy.sh/b/1036654 cYsmix - Fright March] with DT mod");
            addDefault("achievement.25000plays", "无教程信息");
            addDefault("achievement.videogamepackvol4", "无教程信息");
            addDefault("achievement.videogamepackvol3", "无教程信息");
            addDefault("achievement.catch2000000fruits", "无教程信息");
            addDefault("achievement.videogamepackvol2", "无教程信息");
            addDefault("achievement.zestydisposition", " Pass a 3 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (taiko)");
            addDefault("achievement.videogamepackvol1", "无教程信息");
            addDefault("achievement.realitt", " Get 90% or higher accuracy on [https://osu.ppy.sh/s/227126 LeaF - Evanescent] in osu!standard Requires S rank in osu!mania (95%+ accuracy) with 700k+ total score. 93%+ in osu!catch (could be lower?) does not work with HT/EZ/NF");
            addDefault("achievement.slowboat", " Pass any map with HT mod. Can use other mods in the same play.");
            addDefault("achievement.obsessed", " Retry any ranked song 100 times and then pass/full combo it (must be done in 24 hours or less!). Make sure to track your plays using osu! website and NOT in-game playcount tracker. You need to get 10k+ score each play or it won't count. ");
            addDefault("achievement.finality", " Pass any map with SD mod. Can't use any other mods.");
            addDefault("achievement.30000drumhits", "无教程信息");
            addDefault("achievement.perfectionist", " Pass any map with PF mod. Can't use any other mods.");
            addDefault("achievement.everythingextra", " Full combo a 5 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.quickening", " Full combo a 6 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.hourbeforethedawn", " Full combo any difficulty on [https://osu.ppy.sh/b/373781 ginkiha - EOS]</noscript>.");
            addDefault("achievement.sweetandsour", " Full combo a 1 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.anothersurpassed", " Pass a 6 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid [Insane]] +DT  [https://osu.ppy.sh/b/19990 Silver Forest - Tsurupettan] +DT  [https://osu.ppy.sh/b/296 Wizards In Winter [Impossible]] +DT (taiko) ");
            addDefault("achievement.quickdraw", " Be the first one to set a score on a new ranked/qualified map (no scores on ALL difficulties from other players). Works with any osu! game mode. Look for new qualified maps [https://osu.ppy.sh/p/beatmaplist?m=-1&r=11&g=0&la=0&ra= here<a>. NF+DT is recommended for fast passing so no one snipes your achievement. <b>NOTE: a map must be played in it's original game mode. Mania/taiko recommended as not that many people play those game modes.</b>   ");
            addDefault("achievement.slowandsteady", " Pass a 3+ star map with HT+PF mods. [https://osu.ppy.sh/b/1059390 Drop - Granat] [Insane] [https://osu.ppy.sh/b/486513 FELT - In my room] [Tranquility] [https://osu.ppy.sh/b/569636&m=0 Agressor Bunx - Tornado (Original Mix)][Insane]");
            addDefault("achievement.december2017spotlight", "无教程信息");
            addDefault("achievement.aberration", " Full combo an 8 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.movingforward", " Full combo a 4 star map without using EZ/NF/HT mods (don't miss slider ends). [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane]+DT (taiko)");
            addDefault("achievement.aboveandbeyond", " Pass a 6 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid [Insane]] +DT  [https://osu.ppy.sh/b/19990 Silver Forest - Tsurupettan] +DT  [https://osu.ppy.sh/b/296 Wizards In Winter [Impossible]] +DT (taiko) ");
            addDefault("achievement.rhythmgamepackvol4", "无教程信息");
            addDefault("achievement.rhythmgamepackvol3", "无教程信息");
            addDefault("achievement.june2017spotlight", " Clear all maps in June 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 3 maps in osu!mania mode to unlock achievement: [https://osu.ppy.sh/s/529574 1] [https://osu.ppy.sh/s/569903 2] [https://osu.ppy.sh/s/575053 3]");
            addDefault("achievement.myfirstdon", " Pass a 1 star map without using EZ/NF/HT mods.");
            addDefault("achievement.rhythmgamepackvol2", "无教程信息");
            addDefault("achievement.rhythmgamepackvol1", "无教程信息");
            addDefault("achievement.prepared", " Full combo any map with NF mod.[https://osu.ppy.sh/b/34529  Silver Forest - Marisa Spark] is only 21 seconds long.");
            addDefault("achievement.blindsight", " Pass any map with HD mod. Can use other mods in the same play.");
            addDefault("achievement.lordofthecatch", " [https://osu.ppy.sh/b/111680 xi - Ascension to Heaven] +DT [https://osu.ppy.sh/b/39825 IOSYS - Marisa wa Taihen na Kanbu de Tomatte Ikimashita] +DT [https://osu.ppy.sh/s/418826 DystopiaGround - AugoEidEs] +HR (osu!catch) [https://osu.ppy.sh/b/553820 MiddleIsland - Achromat] +DT (osu!mania)");
            addDefault("achievement.400000keys", "无教程信息");
            addDefault("achievement.impeccable", " Pass a 4+ star map with DT+PF mods.   [https://osu.ppy.sh/b/150272 Hatsuki Yura - Yami no Kodomo tachi] [Hard] [https://osu.ppy.sh/b/382042 Hatsuki Yura - Nightmare -Overture-] [Insane]");
            addDefault("achievement.thedemonwithin", " Pass a 5 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/95733 07th Expansion - rog-unlimitation [Insane]] (taiko)");
            addDefault("achievement.scalingup", "无教程信息");
            addDefault("achievement.cleanplatter", " Full combo a 3 star map without using EZ/NF/HT mods (don't miss slider ends). [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (taiko) ");
            addDefault("achievement.thegirlintheforest", " Get 95%+ acc and 151 max combo on [https://osu.ppy.sh/s/40440 S3RL - Pika Girl] Hard or Insane difficulty. Break combo after 151. ");
            addDefault("achievement.1000combo", "无教程信息");
            addDefault("achievement.isthisreallife", " Full combo a map with AR11 OD11 HP11 and over 260BPM.   [https://osu.ppy.sh/b/20737 ERIKA - Destination Nowhere] [Hard] with HDDTHR unlocks this achievement. ");
            addDefault("achievement.internetpackvol4", "无教程信息");
            addDefault("achievement.notimetospare", " Full combo any map with DT mod that is 30 seconds or less (any difficulty). [https://osu.ppy.sh/b/34527 Silver Forest - Marisa Spark] is only 16 seconds long.");
            addDefault("achievement.unstoppable", " Pass a map with AR11 OD11 HP11 and over 260BPM.   [https://osu.ppy.sh/b/20737 ERIKA - Destination Nowhere] [Hard] with HDDTHR unlocks this achievement.");
            addDefault("achievement.internetpackvol2", "无教程信息");
            addDefault("achievement.internetpackvol3", "无教程信息");
            addDefault("achievement.300000drumhits", "无教程信息");
            addDefault("achievement.truetorment", " Pass [https://osu.ppy.sh/s/594751 Helblinde - The Solace of Oblivion] in osu!standard mode");
            addDefault("achievement.nonstopdancer", " Pass [https://osu.ppy.sh/b/9007&m=0 Yoko Ishida - paraparaMAX I<a>. You don't need to full combo, NF mod does not work. ");
            addDefault("achievement.october2017spotlight", " Clear all maps in October 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 3 maps in osu!catch mode to unlock achievement: [https://osu.ppy.sh/s/522132 1] [https://osu.ppy.sh/s/609679 2] [https://osu.ppy.sh/s/665562 3]");
            addDefault("achievement.breakthrough", " Full combo a 4 star map without using EZ/NF/HT mods (don't miss slider ends). [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane]+DT (taiko)");
            addDefault("achievement.dialitrightback", " Pass any map with EZ mod. Can use other mods in the same play.");
            addDefault("achievement.katsukatsukatsu", " Pass a 2 star map without using EZ/NF/HT mods.");
            addDefault("achievement.mappersguildpacki", "无教程信息");
            addDefault("achievement.nevergiveup", " Full combo a 7 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.extracredit", " [https://osu.ppy.sh/b/104229 Team Nekokan - Can't Defeat Airman] [https://osu.ppy.sh/b/736215&m=0 Panda Eyes & Teminite - Highscore] [https://osu.ppy.sh/b/901854 Sendan Life] [https://osu.ppy.sh/b/95733 rog-unlimitation [Insane]] +DT (taiko)");
            addDefault("achievement.betweentherain", " Full combo a 4 star map without using EZ/NF/HT mods (don't miss slider ends). [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane]+DT (taiko)");
            addDefault("achievement.march2017spotlight", " Clear all maps in March 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. NF+DT these 3 maps in osu!catch  to unlock achievement: [https://osu.ppy.sh/s/349949 1] [https://osu.ppy.sh/s/516118 2] [https://osu.ppy.sh/s/544468 3]");
            addDefault("achievement.insanityapproaches", " Pass a 4 star map without using EZ/NF/HT mods. [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane] (osu!standard) [https://osu.ppy.sh/b/315 FAIRY FORE - Vivid] [Insane]+DT (taiko)");
            addDefault("achievement.thefirmamentmoves", " Pass Normal difficulty on [https://osu.ppy.sh/b/1145134 cYsmix - Moonlight Sonata] with HDDTHR mods");
            addDefault("achievement.risingstar", " Pass a 1 star map without using EZ/NF/HT mods.");
            addDefault("achievement.stumbler", " Full combo any map with B rank or lower. ");
            addDefault("achievement.behindtheveil", " Full combo an 8 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.nonstop", " Full combo 10+ minutes of any map (drain time, not including rest time). Can break combo after 10 mins. Easiest way to do this is to full combo [https://osu.ppy.sh/s/2916 Hyadain - Megaman Mix] on osu!catch (CTB) mode with EZ mod. ");
            addDefault("achievement.timeeverlasting", " Full combo a 7 star map without using EZ/NF/HT mods (don't miss slider ends).");
            addDefault("achievement.internetpackvol1", "无教程信息");
            addDefault("achievement.april2017spotlight", " Clear all maps in April 2017 [https://osu.ppy.sh/p/packlist?t=r Spotlight]. Any game mode and difficulty works. NF mod works but needs 10,000+ score to submit play. Complete [https://osu.ppy.sh/s/540175 this map] in osu!mania with NF+DT to unlock achievement.");
            addDefault("achievement.sognare", " Play [https://osu.ppy.sh/b/529285 LeaF - Evanescent] with HT+HD+NF mods (need to get 10,000 score minimum).");

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
