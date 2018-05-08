package cc.moecraft.irc.osubot.osu;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;

/**
 * 此类由 Hykilpikonna 在 2018/04/23 创建!
 * Created by Hykilpikonna on 2018/04/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
@Data
public class Mod
{
    @Getter
    private static HashMap<String, Mod> modReferenceMap = new HashMap<>();

    public static final Mod None = new Mod          (""  , 0);
    public static final Mod NoFail = new Mod        ("NF", 1);
    public static final Mod Easy = new Mod          ("EZ", 2);
    public static final Mod NoVideo = new Mod       (""  , 4); // 官方已弃用
    public static final Mod Hidden = new Mod        ("HD", 8);
    public static final Mod HardRock = new Mod      ("HR", 16);
    public static final Mod SuddenDeath = new Mod   ("SD", 32);
    public static final Mod DoubleTime = new Mod    ("DT", 64);
    public static final Mod Relax = new Mod         ("RX", 128);
    public static final Mod HalfTime = new Mod      ("HT", 256);
    public static final Mod Nightcore = new Mod     ("NC", 512); // Only set along with DoubleTime. i.e: NC only gives 576
    public static final Mod Flashlight = new Mod    ("FL", 1024);
    public static final Mod Autoplay = new Mod      ("", 2048); //TODO: 自动的简写是啥?
    public static final Mod SpunOut = new Mod       ("SO", 4096);
    public static final Mod Relax2 = new Mod        ("AP", 8192);	// Autopilot?
    public static final Mod Perfect = new Mod       ("PF", 16384); // Only set along with SuddenDeath. i.e: PF only gives 16416
    public static final Mod Key4 = new Mod          ("4K", 32768);
    public static final Mod Key5 = new Mod          ("5K", 65536);
    public static final Mod Key6 = new Mod          ("6K", 131072);
    public static final Mod Key7 = new Mod          ("7K", 262144);
    public static final Mod Key8 = new Mod          ("8K", 524288);
    public static final Mod FadeIn = new Mod        ("", 1048576); //TODO: 这三个都是些啥?
    public static final Mod Random = new Mod        ("", 2097152);
    public static final Mod LastMod = new Mod       ("", 4194304);
    public static final Mod Key9 = new Mod          ("9K", 16777216);
    public static final Mod Key10 = new Mod         ("XK", 33554432); // 官方已弃用
    public static final Mod Key1 = new Mod          ("1K", 67108864);
    public static final Mod Key3 = new Mod          ("3K", 134217728);
    public static final Mod Key2 = new Mod          ("2K", 268435456);

    private String shortName;
    private long bitwiseValue;

    public Mod(String shortName, int bitwiseValue)
    {
        this.shortName = shortName;
        this.bitwiseValue = bitwiseValue;

        modReferenceMap.put(shortName, this);
    }
}
