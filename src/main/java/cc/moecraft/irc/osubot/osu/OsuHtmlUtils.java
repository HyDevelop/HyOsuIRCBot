package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.osu.exceptions.JsonNotFoundInHtmlException;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.JsonUtils;
import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@AllArgsConstructor
public class OsuHtmlUtils
{
    @Getter
    private DownloadUtils downloader;

    /**
     * 从Osu的用户界面HTML获取一个Json对象
     * @param jsonId Json的ID
     * @param user 用户ID或用户名
     * @return Json对象
     */
    public JsonElement getJsonElementFromUser(String jsonId, String user) throws MalformedURLException, JsonNotFoundInHtmlException
    {
        return getJsonElementFromOsuWebsite(jsonId, new URL("https://osu.ppy.sh/users/" + user));
    }

    /**
     * 从Osu的HTML中获取一个Json对象
     * @param jsonId Json的ID
     * @param url URL
     * @return Json对象
     */
    public JsonElement getJsonElementFromOsuWebsite(String jsonId, URL url) throws JsonNotFoundInHtmlException
    {
        String html = downloader.downloadAsString(url);

        Pattern pattern = Pattern.compile("<script id=\"json-" + jsonId + "\" type=\"application/json\">(\\S+)</script>");
        Matcher matcher = pattern.matcher(html);

        if (matcher.find())
        {
            String group = matcher.group(1);

            return JsonUtils.parseJsonElement(group);
        }
        else
        {
            throw new JsonNotFoundInHtmlException();
        }
    }
}
