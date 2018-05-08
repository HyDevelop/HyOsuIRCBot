package cc.moecraft.irc.osubot.osu;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@AllArgsConstructor
public class Mods
{
    private long modsInDEC;

    /**
     * 判断一个Mod是否存在
     * @param mod mod
     * @return 是否存在
     */
    public boolean contains(Mod mod)
    {
        return (modsInDEC & mod.getBitwiseValue()) == mod.getBitwiseValue();
    }

    /**
     * 获取所有mod为ArrayList形式
     * @return 所有mod列表
     */
    public ArrayList<Mod> toArray()
    {
        ArrayList<Mod> result = new ArrayList<>();

        Mod.getModReferenceMap().forEach((k, v) ->
        {
            if (contains(v)) result.add(v);
        });

        return result;
    }

    /**
     * 获取所有mod为十进制数格式
     * @return 代表所有mod的十进制数
     */
    public long toDec()
    {
        return modsInDEC;
    }

    /**
     * 添加一个Mod
     * @param mod mod
     * @return 这个实例
     */
    public Mods add(Mod mod)
    {
        modsInDEC = modsInDEC | mod.getBitwiseValue();
        return this;
    }
}
