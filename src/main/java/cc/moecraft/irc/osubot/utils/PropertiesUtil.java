package cc.moecraft.irc.osubot.utils;

import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * 读取配置文件中系统参数配置信息的工具类
 *
 * @author dullwolf
 */
public final class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static final String DEFULT_CONFIG_FILE = "app.properties";//默认的配置文件


    private static class MCDHInstance {
        private static final PropertiesUtil Pro = new PropertiesUtil();
    }

    /**
     * 从默认的配置文件中读取参数值
     *
     * @param key 参数key
     * @return
     */
    public static String readKey(String key) {
        Properties proper = MCDHInstance.Pro.readProperties(DEFULT_CONFIG_FILE);
        return (String) proper.get(key);
    }

    private Properties readProperties(String path) {
        if ("".equals(path) || path == null) {
            path = DEFULT_CONFIG_FILE;
        }
        Properties proper = new Properties();
        try {
            //proper.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(path), "UTF-8"));
            URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
            assert resource != null;
            String filePath = resource.getPath();
            proper.load(new InputStreamReader(new FileInputStream(new File(filePath)), Charsets.UTF_8));
        } catch (Exception e) {
            logger.error("加载系统参数配置文件 app.properties 出错!");
        }
        return proper;
    }


    /**
     * 设置一个属性，如果key已经存在，那么将其对应value值覆盖。
     *
     */
    public static void setProperty(String key, String value) {
        InputStream is = null;
        OutputStream os = null;
        Properties p = new Properties();
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(DEFULT_CONFIG_FILE);
            assert resource != null;
            String filePath = resource.getPath();
            is = new FileInputStream(filePath);
            p.load(is);
            os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(DEFULT_CONFIG_FILE).getFile());
            p.setProperty(key, value);
            p.store(os, key);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
                if (null != os)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
