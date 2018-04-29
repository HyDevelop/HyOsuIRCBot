package com.dullwolf;


import cc.moecraft.irc.osubot.utils.JsonUtils;

import java.util.List;
import java.util.Map;

public class TestJsonArray {


    public static void main(String[] args) {
        String jsonStr = "[\n" +
                "    {\n" +
                "        \"user_id\": \"2660718\",\n" +
                "        \"username\": \"dullwolf\",\n" +
                "        \"count300\": \"1831800\",\n" +
                "        \"count100\": \"170436\",\n" +
                "        \"count50\": \"12688\",\n" +
                "        \"playcount\": \"13656\",\n" +
                "        \"ranked_score\": \"3804055034\",\n" +
                "        \"total_score\": \"11633641105\",\n" +
                "        \"pp_rank\": \"136691\",\n" +
                "        \"level\": \"97.7101\",\n" +
                "        \"pp_raw\": \"1856.16\",\n" +
                "        \"accuracy\": \"96.800537109375\",\n" +
                "        \"count_rank_ss\": \"60\",\n" +
                "        \"count_rank_ssh\": \"11\",\n" +
                "        \"count_rank_s\": \"660\",\n" +
                "        \"count_rank_sh\": \"68\",\n" +
                "        \"count_rank_a\": \"216\",\n" +
                "        \"country\": \"CN\",\n" +
                "        \"pp_country_rank\": \"5471\",\n" +
                "        \"events\": [\n" +
                "            {\n" +
                "                \"display_html\": \"<img src='/images/S_small.png'/> <b><a href='/u/2660718'>dullwolf</a></b> achieved rank #283 on <a href='/b/1428999?m=0'>sasakure.UK x DECO*27 - 39 [Chekito's Insane]</a> (osu!)\",\n" +
                "                \"beatmap_id\": \"1428999\",\n" +
                "                \"beatmapset_id\": \"667433\",\n" +
                "                \"date\": \"2018-04-29 00:38:26\",\n" +
                "                \"epicfactor\": \"1\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";

        List<Map> mapList = JsonUtils.getArrayByJson(jsonStr, Map.class);
        System.out.println(mapList.toString());


    }

}
