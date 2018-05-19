package cc.moecraft.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 此类由 Hykilpikonna 在 2018/05/19 创建!
 * Created by Hykilpikonna on 2018/05/19!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class RandomTest
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        System.out.println(list.get(new Random().nextInt(list.size())));
    }
}
