package cc.moecraft.yaml.utils;

import java.io.File;
import java.io.IOException;

/**
 * 此类由 Hykilpikonna 在 2017/09/13 创建!
 * Created by Hykilpikonna on 2017/09/13!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FileUtils 
{
    /**
     * 创建文件
     * @param file 文件
     * @return 是否成功
     */
    public static boolean createFile(File file)
    {
        File absoluteFile = file.getAbsoluteFile();

        //ConfigLibTest.getLogger().debug("File = " + absoluteFile.toString());
        //ConfigLibTest.getLogger().debug("AbsoluteFile = " + absoluteFile.toString());

        if(absoluteFile.exists())
        {
            System.out.println("[MoeConfig] 创建文件" + absoluteFile + "失败: 已存在");
            return false;
        }
        if (absoluteFile.toString().endsWith(File.separator))
        {
            System.out.println("[MoeConfig] 创建文件" + absoluteFile + "失败: 目标文件不能为目录");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!absoluteFile.getParentFile().exists())
        {
            //如果目标文件所在的目录不存在，则创建父目录
            //tempDebug("目标文件所在目录不存在, 正在创建...");
            if(!absoluteFile.getParentFile().mkdirs())
            {
                System.out.println("[MoeConfig] 创建文件" + absoluteFile + "失败: 创建目标文件所在目录失败");
                return false;
            }
        }
        //创建目标文件
        try
        {
            if (absoluteFile.createNewFile())
            {
                //tempDebug(GREEN + "创建文件" + file + "成功");
                return true;
            }
            else
            {
                System.out.println("[MoeConfig] 创建文件" + absoluteFile + "失败: 原因未知");
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println("[MoeConfig] 创建文件" + absoluteFile + "失败, 原因如下:");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建目录
     * @param destDirName 目录
     * @return 是否成功
     */
    public static boolean createDir(String destDirName)
    {
        File dir = new File(destDirName);
        if (dir.exists())
        {
            System.out.println("[MoeConfig] 创建目录" + dir.getAbsolutePath() + "失败: 已存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator))
        {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs())
        {
            //tempDebug(GREEN + "创建目录" + destDirName + "成功!");
            return true;
        }
        else
        {
            System.out.println("[MoeConfig] 创建目录" + dir.getAbsolutePath() + "失败: 未知原因");
            return false;
        }
    }

    public static String combinePath(String path1, String path2)
    {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);
        return file2.getPath();
    }

    public static String createTempFile(String prefix, String suffix, String dirName)
    {
        File tempFile;
        if (dirName == null)
        {
            try
            {
                //在默认文件夹下创建临时文件
                tempFile = File.createTempFile(prefix, suffix);
                //返回临时文件的路径
                return tempFile.getCanonicalPath();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                //tempDebug("创建临时文件失败！" + e.getMessage());
                return null;
            }
        }
        else
        {
            File dir = new File(dirName);
            //如果临时文件所在目录不存在，首先创建
            if (!dir.exists())
            {
                if (!createDir(dirName))
                {
                    //tempDebug("创建临时文件失败，不能创建临时文件所在的目录！");
                    return null;
                }
            }
            try
            {
                //在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                //tempDebug("创建临时文件失败！" + e.getMessage());
                return null;
            }
        }
    }
}
