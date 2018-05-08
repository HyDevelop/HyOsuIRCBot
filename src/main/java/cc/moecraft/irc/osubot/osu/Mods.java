package cc.moecraft.irc.osubot.osu;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@AllArgsConstructor
@NoArgsConstructor
public class Mods
{
    private long modsInDEC = 0;

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

    /**
     * 添加一个Mod的封装, 添加多个mod
     * @param mods mod
     * @return 这个实例
     */
    public Mods add(Mod ... mods)
    {
        for (Mod oneMod : mods) add(oneMod);
        return this;
    }

    /**
     * 移除一个Mod
     * @param mod mod
     * @return 这个实例
     */
    public Mods remove(Mod mod)
    {
        modsInDEC = modsInDEC ^ mod.getBitwiseValue();
        return this;
    }

    /**
     * 移除一个Mod的封装, 移除多个mod
     * @param mods mod
     * @return 这个实例
     */
    public Mods remove(Mod ... mods)
    {
        for (Mod oneMod : mods) remove(oneMod);
        return this;
    }

    /**
     * 获取所有mod合在一起的名字
     * Ex: "HD DT HR"
     *
     * @return 所有mod名字
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        ArrayList<Mod> modsArrayList = toArray();

        if (modsArrayList.size() == 0) return "";
        
        builder.append(modsArrayList.get(0).getShortName());
        modsArrayList.remove(0);

        modsArrayList.forEach(oneMod -> builder.append(" ").append(oneMod.getShortName()));

        return builder.toString();
    }
}
