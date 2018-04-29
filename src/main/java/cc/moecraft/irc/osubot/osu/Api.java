package cc.moecraft.irc.osubot.osu;


public enum Api {

    User          ("https://osu.ppy.sh/api/get_user"),
    Score         ("https://osu.ppy.sh/api/get_scores"),
    Recent        ("https://osu.ppy.sh/api/get_user_recent");

    public String apiUrl;

    Api(String value)
    {
        this.apiUrl = value;
    }
}
