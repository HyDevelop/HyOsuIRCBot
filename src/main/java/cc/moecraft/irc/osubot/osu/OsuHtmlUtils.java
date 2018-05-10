package cc.moecraft.irc.osubot.osu;

import cc.moecraft.irc.osubot.achievement.Achievement;
import cc.moecraft.irc.osubot.osu.data.AchievementData;
import cc.moecraft.irc.osubot.osu.exceptions.JsonEmptyException;
import cc.moecraft.irc.osubot.osu.exceptions.JsonNotFoundInHtmlException;
import cc.moecraft.irc.osubot.osu.exceptions.UnexpectedHtmlJsonException;
import cc.moecraft.irc.osubot.osu.exceptions.UserNotFoundException;
import cc.moecraft.irc.osubot.utils.DownloadUtils;
import cc.moecraft.irc.osubot.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jfinal.json.Json;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public ArrayList<JsonElement> getJsonElementFromUser(String user, String ... jsonIds) throws MalformedURLException {
        return getJsonElementsFromOsuWebsite(new ArrayList<>(Arrays.asList(jsonIds)), new URL("https://osu.ppy.sh/users/" + user));
    }

    /**
     * 从Osu的HTML中获取一个Json对象
     *
     * @param jsonIds Json的ID
     * @param url URL
     * @return Json对象
     */
    public ArrayList<JsonElement> getJsonElementsFromOsuWebsite(ArrayList<String> jsonIds, URL url)
    {
        ArrayList<String> tags = getTagsFromJsonIds(jsonIds);
        ArrayList<JsonElement> result = new ArrayList<>();

        String html = downloader.downloadAsString(url);

        Scanner scanner = new Scanner(html);

        while (scanner.hasNext())
        {
            String line = scanner.nextLine();

            if (!line.contains(TAGS_PREFIX)) continue;

            for (String tag : tags)
            {
                if (!line.contains(tag)) continue;

                result.add(new JsonParser().parse(scanner.nextLine()));
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
     * 获取用户成就组
     * @param user 用户ID或用户名
     * @return 成就列表
     */
    public ArrayList<AchievementData> getAchievements(String user) throws UnexpectedHtmlJsonException, MalformedURLException, JsonEmptyException
    {
        ArrayList<JsonElement> tempElements = getJsonElementFromUser(user, "achievements");

        if (tempElements.size() < 1) throw new JsonEmptyException();
        if (tempElements.size() > 1) throw new UnexpectedHtmlJsonException("获取到的Json不止一个", null, tempElements);

        JsonElement tempElement = tempElements.get(0);

        if (tempElement.isJsonNull()) throw new JsonEmptyException();
        if (!tempElement.isJsonArray()) throw new UnexpectedHtmlJsonException("获取到的Json不是列表", null, tempElements);

        JsonArray array = tempElement.getAsJsonArray();

        if (array.size() < 1) throw new UnexpectedHtmlJsonException("成就列表怎么一项都没有???!", null, tempElements);

        ArrayList<AchievementData> result = new ArrayList<>();

        array.forEach(element ->
        {
            result.add(new Gson().fromJson(element, AchievementData.class));
        });

        return result;


        /*String html = downloader.downloadAsString(new URL("https://osu.ppy.sh/users/" + user));

        // Pattern pattern = Pattern.compile("(?<=<script id=\"json-achievements\" type=\"application\\/json\">)(.*([\\d\\r\\n]+))(?= {8}<\\/script>)", Pattern.DOTALL);
        Pattern pattern = Pattern.compile("\\b.*<script id=\"json-achievements\" type=\"application\\/json\">\\s+\\S+.*]", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);

        // if (!matcher.find()) throw new UserNotFoundException();

        while (matcher.find())
        {
            System.out.println(matcher.group(1));
        }

        String group = matcher.group(0);
        JsonElement jsonElement = JsonUtils.parseJsonElement(group);

        if (jsonElement.isJsonNull()) throw new JsonEmptyException();
        if (!jsonElement.isJsonArray()) throw new UnexpectedHtmlJsonException("这个获取到的Json不是JsonArray, 也不是空的", html, jsonElement.toString());

        ArrayList<AchievementData> achievements = new ArrayList<>();

        for (JsonElement oneElement : jsonElement.getAsJsonArray())
        {
            if (oneElement.isJsonNull()) continue;
            if (!oneElement.isJsonObject()) throw new UnexpectedHtmlJsonException("这个获取到的Json中的一项不是JsonObject, 也不是空的", html, oneElement.toString());

            achievements.add(new Gson().fromJson(oneElement.getAsJsonObject(), AchievementData.class));
        }

        return achievements;*/
    }
}
