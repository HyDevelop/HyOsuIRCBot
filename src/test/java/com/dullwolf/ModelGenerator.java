package com.dullwolf;


import io.jboot.Jboot;
import io.jboot.codegen.model.JbootModelGenerator;

public class ModelGenerator {

    public static void main(String[] args){
        Jboot.setBootArg("jboot.datasource.url", "jdbc:mysql://119.29.222.57:3306/osuIrcBot?characterEncoding=utf8&useSSL=true");
        Jboot.setBootArg("jboot.datasource.user", "root");
        Jboot.setBootArg("jboot.datasource.password", "dw123456");
        String modelPackage="cc.moecraft.irc.osubot.model";
        JbootModelGenerator.run(modelPackage);
    }
}
