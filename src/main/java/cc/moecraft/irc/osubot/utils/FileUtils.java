package cc.moecraft.irc.osubot.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 此类由 Hykilpikonna 在 2018/05/05 创建!
 * Created by Hykilpikonna on 2018/05/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class FileUtils
{
    /**
     * 递归获取所有文件夹下的所有文件
     *
     * @param existingList 已经有的列表
     * @param path 根目录
     * @return 所有文件
     */
    public static ArrayList<File> getAllFiles(ArrayList<File> existingList, File path)
    {
        if (path.isFile())
        {
            if (!existingList.contains(path)) existingList.add(path);
            return existingList;
        }

        for (File file : Objects.requireNonNull(path.listFiles()))
        {
            existingList = getAllFiles(existingList, file);
        }

        return existingList;
    }

    public static ArrayList<File> getAllFiles(File path)
    {
        return getAllFiles(new ArrayList<>(), path);
    }

    /**
     * 获取文件后缀
     *
     * @param file 文件
     * @return 文件后缀
     */
    public static String getFileExtension(File file)
    {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    /**
     * 获取所有Java文件
     *
     * @param allFiles 所有文件
     * @return 所有java文件
     */
    public static ArrayList<File> getAllJavaFiles(ArrayList<File> allFiles)
    {
        ArrayList<File> filterResult = new ArrayList<>();

        allFiles.forEach(file ->
        {
            if (FileUtils.getFileExtension(file).equals("java")) filterResult.add(file);
        });

        return filterResult;
    }
}
