package cc.moecraft.irc.osubot.achievement;

import cc.moecraft.irc.osubot.command.Command;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AchievementManager
{
    @Getter
    private HashMap<String, Achievement> achievements = new HashMap<>();

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

            achievements.put(achievement.officialAchievementName(), achievement);
        }
    }
}
