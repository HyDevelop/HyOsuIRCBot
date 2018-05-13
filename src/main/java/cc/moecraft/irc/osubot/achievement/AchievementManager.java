package cc.moecraft.irc.osubot.achievement;

import cc.moecraft.irc.osubot.osu.data.AchievementData;
import cc.moecraft.irc.osubot.osu.exceptions.UnexpectedAchievementNotFoundException;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AchievementManager
{
    @Getter
    private HashMap<String, Achievement> achievements = new HashMap<>();
    private HashMap<String, Achievement> achievementsByLowerCaseName = new HashMap<>();
    private HashMap<Long, Achievement> achievementsById = new HashMap<>();

    public AchievementManager() throws InstantiationException, IllegalAccessException
    {
        addAllAchievements();
    }

    /**
     * 自动识别所有成就
     */
    public void addAllAchievements() throws IllegalAccessException, InstantiationException
    {
        Reflections reflections = new Reflections("cc.moecraft.irc.osubot.achievement.achievements");

        // 获取包下的所有继承Achievement的类
        Set<Class<? extends Achievement>> classes = reflections.getSubTypesOf(Achievement.class);

        // 循环注册
        for (Class<? extends Achievement> oneClass : classes)
        {
            Achievement achievement = oneClass.newInstance();

            achievements.put(achievement.getName(), achievement);
            achievementsByLowerCaseName.put(achievement.getName().toLowerCase().replaceAll("[^A-Za-z0-9 ]", ""), achievement);
            achievementsById.put(achievement.getId(), achievement);
        }
    }

    /**
     * 通过从JSON获取到的成就获取成就
     * @param achievementData 获取到的成就数据
     * @return 成就
     */
    public Achievement getAchievement(AchievementData achievementData) throws UnexpectedAchievementNotFoundException
    {
        for (Map.Entry<String, Achievement> achievementEntry : achievements.entrySet())
        {
            if (achievementEntry.getValue().getId() == achievementData.getId()) return achievementEntry.getValue();
        }

        throw new UnexpectedAchievementNotFoundException(achievementData);
    }

    /**
     * 通过名字获取成就
     * @param name 名字
     * @return 成就
     */
    public Achievement findAchievementByName(String name)
    {
        return achievements.getOrDefault(name.toLowerCase().replaceAll("[^A-Za-z0-9 ]", ""), null);
    }

    /**
     * 通过id获取成就
     * @param id id
     * @return 成就
     */
    public Achievement findAchievementById(long id)
    {
        return achievementsById.getOrDefault(id, null);
    }
}
