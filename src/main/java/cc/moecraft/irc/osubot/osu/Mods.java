package cc.moecraft.irc.osubot.osu;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@AllArgsConstructor @Data
public class Mods
{
    private long modsInDEC;

    public boolean contains(Mod mod)
    {
        return (modsInDEC & mod.getValue()) == mod.getValue();
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
}
