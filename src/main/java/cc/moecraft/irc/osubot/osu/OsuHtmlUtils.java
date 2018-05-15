package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.osu.data.AchievementData;
import cc.moecraft.irc.osubot.osu.data.web.WebsiteUserData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.UnexpectedHtmlJsonException;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@AllArgsConstructor
public class OsuHtmlUtils
{
    @Getter
    private DownloadUtils downloader;

    private static final String TAGS_PREFIX = "<script id=\"json-";
    private static final String TAGS_SUFFIX = "\" type=\"application/json\">";

    /**
     * 从Osu的用户界面HTML获取一个Json对象
     * @param jsonIds Json的ID
     * @param user 用户ID或用户名
     * @return Json对象
     */
    public HashMap<String, JsonElement> getJsonElementFromUser(String user, String ... jsonIds) throws MalformedURLException
    {
        return getJsonElementsFromOsuWebsite(new ArrayList<>(Arrays.asList(jsonIds)), new URL("https://osu.ppy.sh/users/" + user));
    }

    /**
     * 从Osu的HTML中获取一个Json对象
     *
     * @param jsonIds Json的ID
     * @param url URL
     * @return Json对象
     */
    public HashMap<String, JsonElement> getJsonElementsFromOsuWebsite(ArrayList<String> jsonIds, URL url)
    {
        ArrayList<String> tags = getTagsFromJsonIds(jsonIds);
        HashMap<String, String> tagData = getTagDataFromWebsite(tags, url, 1, "</script>");

        HashMap<String, JsonElement> result = new HashMap<>();

        tagData.forEach((tag, data) -> result.put(getTagsFromJsonIdsReverse(tag), new JsonParser().parse(data)));

        return result;
    }

    /**
     * 从一个网站中获取一些Tag
     * @param tags 这些Tag
     * @param url 网站URL
     * @param ignoranceIndex 忽略几行
     * @param endLineContains 扫描到什么结束
     * @return 结果 ( Tag 对应 数据 )
     */
    public HashMap<String, String> getTagDataFromWebsite(ArrayList<String> tags, URL url, int ignoranceIndex, String endLineContains)
    {
        HashMap<String, String> result = new HashMap<>();

        String html = downloader.downloadAsString(url);

        Scanner scanner = new Scanner(html);

        while (scanner.hasNext())
        {
            String line = scanner.nextLine();

            if (!line.contains(TAGS_PREFIX)) continue;

            for (String tag : tags)
            {
                if (!line.contains(tag)) continue;

                for (int i = 1; i < ignoranceIndex; i++) scanner.nextLine(); // 忽略掉这些行

                StringBuilder builder = new StringBuilder();

                while (scanner.hasNext())
                {
                    String nextLine = scanner.nextLine();

                    if (nextLine.contains(endLineContains)) break;

                    if (!builder.toString().equals("")) builder.append("\n");
                    builder.append(nextLine);
                }

                result.put(tag, builder.toString());
            }
        }

        return result;
    }

    /**
     * 从Id获取HTML的Tags
     *
     * 例子:
     *   ids: achievement, currentMode
     * 返回:
     *   tags: <script id="json-achievement" type="application/json">, <script id="json-currentMode" type="application/json">
     *
     * @param jsonIds Id列表
     * @return Tag列表
     */
    public ArrayList<String> getTagsFromJsonIds(ArrayList<String> jsonIds)
    {
        ArrayList<String> tags = new ArrayList<>();

        jsonIds.forEach(jsonId -> tags.add(TAGS_PREFIX + jsonId + TAGS_SUFFIX));

        return tags;
    }

    /**
     * 反向处理, 从HTML的Tags获取Id
     * @param tags Id列表
     * @return Tag列表
     */
    public ArrayList<String> getTagsFromJsonIdsReverse(ArrayList<String> tags)
    {
        ArrayList<String> jsonIds = new ArrayList<>();

        tags.forEach(tag -> jsonIds.add(tag.replace(TAGS_PREFIX, "").replace(TAGS_SUFFIX, "")));

        return jsonIds;
    }

    /**
     * 反向处理, 从HTML的Tags获取Id
     * @param tag Id
     * @return Tag
     */
    public String getTagsFromJsonIdsReverse(String tag)
    {
        return tag.replace(TAGS_PREFIX, "").replace(TAGS_SUFFIX, "");
    }

    /**
     * 获取所有成就组
     * // @param user 用户ID或用户名
     * @return 成就列表
     */
    public ArrayList<AchievementData> getAllAvailableAchievements() throws UnexpectedHtmlJsonException, MalformedURLException, JsonEmptyException
    {
        HashMap<String, JsonElement> tempElements = getJsonElementFromUser("5093373", "achievements");

        if (tempElements.size() < 1) throw new JsonEmptyException();
        if (tempElements.size() > 1) throw new UnexpectedHtmlJsonException("获取到的Json不止一个", null, tempElements);

        JsonElement tempElement = tempElements.get("achievements");

        if (tempElement.isJsonNull()) throw new JsonEmptyException();
        if (!tempElement.isJsonArray()) throw new UnexpectedHtmlJsonException("获取到的Json不是列表", null, tempElements);

        JsonArray array = tempElement.getAsJsonArray();

        if (array.size() < 1) throw new UnexpectedHtmlJsonException("成就列表怎么一项都没有???!", null, tempElements);

        ArrayList<AchievementData> result = new ArrayList<>();

        array.forEach(element -> result.add(new Gson().fromJson(element, AchievementData.class)));

        return result;
    }

    /**
     * 用用户名或者id从官网获取用户信息
     * @param usernameOrId 用户名或者id
     * @return 用户信息
     */
    public WebsiteUserData getWebUserData(String usernameOrId)
    {
        try
        {
            JsonElement jsonElement = getJsonElementFromUser(usernameOrId, "user").get("user");

            return new Gson().fromJson(jsonElement, WebsiteUserData.class);
        }
        catch (MalformedURLException e)
        {
            // 这里永远不会发生
            e.printStackTrace();
            return null;
        }
    }
}
