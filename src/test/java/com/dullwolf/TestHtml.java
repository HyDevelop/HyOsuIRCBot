package com.dullwolf;


import cc.moecraft.irc.osubot.osu.util.SpiderHtml;

import java.util.HashSet;
import java.util.Set;

public class TestHtml {

    public static void main(String[] args) {
        Set<String> keys = new HashSet<>();
        keys.add("registered players");
        keys.add("online");
        String info = SpiderHtml.spiderByKey("https://osu.ppy.sh/", keys);
        System.out.println(info);
    }

}
