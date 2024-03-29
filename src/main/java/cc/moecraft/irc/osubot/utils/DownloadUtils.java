package cc.moecraft.irc.osubot.utils;

import com.google.gson.JsonElement;
import io.jboot.Jboot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * 此类由 Hykilpikonna 在 2018/04/23 创建!
 * Created by Hykilpikonna on 2018/04/23!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
@AllArgsConstructor
public class DownloadUtils
{
    @Getter @Setter
    private int timeout;

    public JsonElement getJsonElementFromURL(String url) throws MalformedURLException
    {
        return getJsonElementFromURL(url, timeout);
    }

    /**
     * 从URL获取JSON对象
     *
     * @param url URL
     * @return JSON对象
     * @exception MalformedURLException URL解析失败
     */
    public static JsonElement getJsonElementFromURL(String url, int timeout) throws MalformedURLException
    {
        return JsonUtils.parseJsonElement(downloadAsString(new URL(url), timeout));
    }

    public String downloadAsString(URL url)
    {
        return downloadAsString(url, timeout);
    }

    /**
     * 下载HTTP数据为字符串
     *
     * @param url 下载地址
     * @return 下载到的字符串
     */
    public static String downloadAsString(URL url, int timeout)
    {
        try
        {
            return new String(Objects.requireNonNull(download(url, timeout)));
        }
        catch (Exception e)
        {
            return null;
        }
        // return Jboot.httpPost(url.toString());
    }

    public static String jbootdownloadAsString(URL url)
    {
        return Jboot.httpPost(url.toString());
    }

    @Deprecated
    public byte[] download(URL url)
    {
        return download(url, timeout);
    }

    /**
     * 下载HTTP数据
     *
     * @param url 下载地址
     * @return 下载到的内容
     */
    @Deprecated
    public static byte[] download(URL url, int timeout)
    {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(timeout);
            httpURLConnection.setReadTimeout(timeout);
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            httpURLConnection.setRequestProperty("Accept","*/*");
            httpURLConnection.setRequestMethod("GET");

            InputStream inputStream = httpURLConnection.getInputStream();

            byte[] data = readInputStream(inputStream);
            inputStream.close();

            return data;
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取InputStream
     * 归功于 @V乐乐
     * Credit to @Vlvxingze
     *
     * @param inputStream InputStream
     * @return 读取为字节数组
     * @throws IOException 抛出异常
     */
    @Deprecated
    public static byte[] readInputStream(InputStream inputStream) throws IOException
    {
        byte[] buffer = new byte[1024];
        int len;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) outputStream.write(buffer, 0, len);
        outputStream.close();

        return outputStream.toByteArray();
    }
}
