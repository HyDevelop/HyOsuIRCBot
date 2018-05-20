package cc.moecraft.irc.osubot.language;

import cc.moecraft.irc.osubot.Main;

/**
 * 此类由 Hykilpikonna 在 2018/05/18 创建!
 * Created by Hykilpikonna on 2018/05/18!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class RandomMLT extends MultiLanguageText
{
    /**
     * 随机的多语言对象
     * @param languageNode 语言节点
     */
    public RandomMLT(String languageNode)
    {
        super(languageNode, Type.LANGUAGE_NODE);
    }

    /**
     * 获取随机消息
     * @param lang 语言
     * @return 随机消息
     */
    @Override
    public String getText(String lang)
    {
        return Main.getMessenger().getLanguageFileManager().getRandomInAList(lang, getTextRaw());
    }
}
