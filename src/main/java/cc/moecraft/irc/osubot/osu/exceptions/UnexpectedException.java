package cc.moecraft.irc.osubot.osu.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

/**
 * 此类由 Hykilpikonna 在 2018/05/08 创建!
 * Created by Hykilpikonna on 2018/05/08!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Data
public class UnexpectedException extends Exception
{
    private final String description;
    HashMap<String, Object> additionalObjects = new HashMap<>();

    // 这个是不正常情况的异常.... ( 好像很有道理!
    // 嗯... 因为大部分异常都用到正常一点的返回情况了,
    // 这个类和子类都是些正常情况不会发生的情况的异常
    // 例子:
    //      用户没找到就是正常情况的异常, 不能用这个类
    //      游戏模式既不是STD也不是Mania, Taiko, CTB, 就是正常情况不可能发生的异常, 通过这个类抛出

    @Override //TODO: 用Log4J的Error, 或者其他方式收集报错信息
    public void printStackTrace()
    {
        System.out.println("发生未知异常! " + description);

        additionalObjects.forEach((k, v) ->
        {
            System.out.println(" ");
            System.out.println("附加变量 " + k + " :");
            System.out.println(v.toString());
        });

        super.printStackTrace();
    }
}
