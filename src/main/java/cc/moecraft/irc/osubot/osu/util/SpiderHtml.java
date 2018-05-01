package cc.moecraft.irc.osubot.osu.util;


import cc.moecraft.irc.osubot.utils.StringUtils;
import com.jfinal.kit.StrKit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;

public class SpiderHtml {

    private static BufferedReader getBr(String srcUrl) {
        BufferedReader br = null;
        try {
            URL url = new URL(srcUrl);
            URLConnection urlConn = url.openConnection();
            urlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return br;
    }

    public static String spiderByKey(String srcUrl,Set<String> keys) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = getBr(srcUrl);
        String line;
        int count = keys.size();
        try {
            while ((line = br.readLine()) != null) {
                if(0 == count){
                    break;
                }
                if(StrKit.isBlank(line)){
                    continue;
                }
                for (String key: keys) {
                    if(line.contains(key)){
                        count --;
                        sb.append(StringUtils.guoHtml(line.trim())).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
