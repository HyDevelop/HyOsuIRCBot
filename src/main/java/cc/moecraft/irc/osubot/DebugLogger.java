package cc.moecraft.irc.osubot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 此类由 Hykilpikonna 在 2018/04/17 创建!
 * Created by Hykilpikonna on 2018/04/17!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
public class DebugLogger
{
    // Reset
    public static final String RESET = "\033[0m";  // 重置

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // 黑
    public static final String RED = "\033[0;31m";     // 红
    public static final String GREEN = "\033[0;32m";   // 绿
    public static final String YELLOW = "\033[0;33m";  // 黄
    public static final String BLUE = "\033[0;34m";    // 蓝
    public static final String PURPLE = "\033[0;35m";  // 紫
    public static final String CYAN = "\033[0;36m";    // 青
    public static final String WHITE = "\033[0;37m";   // 白
    
    private String pre = "";
    private boolean debug = false;

    /**
     * 一个萌萌的Logger
     * @param pre 前缀
     * @param debug Debug消息是否开启
     */
    public DebugLogger(String pre, boolean debug)
    {
        this.pre = pre;
        this.debug = debug;
    }

    /**
     * 设置是否Debug
     * @param value 是否Debug
     */
    public void setDebug(boolean value)
    {
        debug = value;
    }

    /**
     * 发送一条log
     * @param s log消息
     */
    public void log(String s)
    {
        System.out.println(String.format("%s[%s%s%s] [%s%s%s] %s%s", WHITE, PURPLE, getCurrentTime(), WHITE, BLUE, pre, WHITE, RESET, s));
    }

    public String getCurrentTime()
    {
        return new SimpleDateFormat("yy-MM-dd HH.mm.ss").format(Calendar.getInstance().getTime());
    }

    /**
     * 发送一条Debug消息
     * @param s Debug消息
     */
    public void debug(String s)
    {
        if (debug)
        {
            StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];

            log(String.format("%s[%sDEBUG%s(%s%s.%s.%s%s)] %s%s", WHITE, RED, WHITE, YELLOW, stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getLineNumber(), WHITE, CYAN, s));
        }
    }
}

