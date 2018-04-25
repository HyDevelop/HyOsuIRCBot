package cc.moecraft.irc.osubot.osu;

/**
 * 此类由 Hykilpikonna 在 2018/04/23 创建!
 * Created by Hykilpikonna on 2018/04/23!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public enum Mods
{
    None          (0),
    NoFail        (1),
    Easy          (2),
    NoVideo       (4), // Not used anymore), but can be found on old plays like Mesita on b/78239
    Hidden        (8),
    HardRock      (16),
    SuddenDeath   (32),
    DoubleTime    (64),
    Relax         (128),
    HalfTime      (256),
    Nightcore     (512), // Only set along with DoubleTime. i.e: NC only gives 576
    Flashlight    (1024),
    Autoplay      (2048),
    SpunOut       (4096),
    Relax2        (8192),	// Autopilot?
    Perfect       (16384), // Only set along with SuddenDeath. i.e: PF only gives 16416
    Key4          (32768),
    Key5          (65536),
    Key6          (131072),
    Key7          (262144),
    Key8          (524288),
    FadeIn        (1048576),
    Random        (2097152),
    LastMod       (4194304),
    Key9          (16777216),
    Key10         (33554432),
    Key1          (67108864),
    Key3          (134217728),
    Key2          (268435456);

    int value;

    Mods(int value)
    {
        this.value = value;
    }
}