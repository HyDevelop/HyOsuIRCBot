package cc.moecraft.irc.osubot;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 此类由 Hykilpikonna 在 2018/05/13 创建!
 * Created by Hykilpikonna on 2018/05/13!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data @AllArgsConstructor
public class BotAccount
{
    String username;
    String password;
    boolean channel;
}
