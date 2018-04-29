package com.dullwolf;


import cc.moecraft.irc.osubot.utils.PropertiesUtil;

public class TestProperties {

    private static String osuKey = PropertiesUtil.readKey("osu_key");

    public static void main(String[] args) {
        System.out.println(osuKey);
    }

}
