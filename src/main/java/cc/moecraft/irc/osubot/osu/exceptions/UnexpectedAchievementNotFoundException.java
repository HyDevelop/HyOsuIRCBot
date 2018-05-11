package cc.moecraft.irc.osubot.osu.exceptions;

import cc.moecraft.irc.osubot.osu.data.AchievementData;
import com.google.gson.Gson;

/**
 * 此类由 Hykilpikonna 在 2018/05/10 创建!
 * Created by Hykilpikonna on 2018/05/10!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class UnexpectedAchievementNotFoundException extends UnexpectedException
{
    public UnexpectedAchievementNotFoundException(AchievementData achievementData)
    {
        super("错误, 成就" + achievementData.getId() + "未找到");

        additionalObjects.put("Achievement", new Gson().toJson(achievementData));
    }
}
