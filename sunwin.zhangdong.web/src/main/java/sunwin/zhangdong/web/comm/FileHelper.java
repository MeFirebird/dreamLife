package sunwin.zhangdong.web.comm;

import org.springframework.stereotype.Component;
import sunwin.zhangdong.comm.StringUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by mt on 2015-8-22.
 */
@Component
public class FileHelper {
    /**
     * 创建文件夹     注意目录和文件    目录文件夹，包含文件
     */
    public static boolean mkdir(String path) {

        path = normalizePath(path);      // 将文件路径规则化
        File dir = new File(path);       // 创建文件逻辑实例
        // 判断File对象对应的文件或目录是否存在
        if (!dir.exists()) {             // 判断此文件物理实例是否创建过，没创建过就创建。
            // 调用mkdirs()的时候File对象必须对应一个路径，而不是一个文件哦
            dir.mkdirs();   // 创建一个File对象所对应的目录 成功返回true
        }
        return true;
    }

    /**
     * 将文件路径规则化，去掉其中多余的/和\，去掉可能造成文件信息泄漏的../
     */
    public static String normalizePath(String path) {

        path = path.replace('\\', '/');
        path = StringUtil.replaceEx(path, "../", "/");
        path = StringUtil.replaceEx(path, "./", "/");
        if (path.endsWith("..")) {
            path = path.substring(0, path.length() - 2);
        }
        path = path.replaceAll("/+", "/");
        return path;
    }



    /**
     * 创建新文件
     */
    public  static void createNewFile(String fileName) throws Exception{
        if(!existFile(fileName)){      // File对象对应的文件或目录是否存在
            File file=new File(fileName);   // 这句放在if判断前， 或者压根就不需要吧！！！！！！
            file.createNewFile();   //当前File对象对应的文件不存在时，创建该文件
        }
    }
    /**
     * 判断文件是否存在
     */
    public static boolean existFile(String fileName){
        File file=new File(fileName);
        if(file.exists()){    // 判断File对象所对应的文件或目录是否存在
            return true;
        }
        return false;
    }

    /**
     *
     * @param fileName
     */
    public static void delFile(String fileName){
        File file=new File(fileName);   // 创建一个File对象
        if(file.exists()){      //file对象对应的文件或者目录是否存在
            file.delete();     // 删除File对象所对应的文件或路径
        }
    }
    /**
     * 获取文件后缀名
     *
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');  // 返回‘.’在字符串中出现的位置下标
            //  "." 不是最后一位（也即文件名最后一位是“.”的排除）
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 删除文件夹。包括文件
     *
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);  // 创建目录/文件夹
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 新建文件
     *
     * @param filePathAndName
     *            String 文件路径及名称 如c:/fqf.txt
     * @param fileContent
     *            String 文件内容
     * @return boolean
     */
    public static void newFile(String filePathAndName, String fileContent) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();     // 多余的吧
            File myFilePath = new File(filePath);  // 创建File对象
            if (!myFilePath.exists()) {       //  File对象对应的文件或目录是否存在
                myFilePath.createNewFile();   // 创建一个该File对象指定的新文件。 成功返回true
            }
            // 文件转换为对象后，通过流访问文件内容， 字符输出流。 创建访问文件对象的流。
            FileWriter resultFile = new FileWriter(myFilePath);
            // 字符输出流  打印流   输出流---->打印流
            PrintWriter myFile = new PrintWriter(resultFile);
            String strContent = fileContent;
            myFile.println(strContent);   // 将文件内容写入到打印流中
            resultFile.close(); // 关闭文件访问流

        } catch (Exception e) {
            System.out.println("error create file!");
            e.printStackTrace();

        }

    }
    /**
     * 随机文件名
     */
    public String createFileName(){
        long l=System.currentTimeMillis();
        return l+"";
    }
    /**
     * 删除指定文件夹下面的所有文件
     *
     * @param path
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);   // 创建File对象（文件或者目录）
        if (!file.exists()) {   // File对象对应的文件或目录是否存在， 存在就继续执行
            return flag;
        }
        if (!file.isDirectory()) {   // File对象所对应的是否是目录，不是目录，就终止
            return flag;
        }
        String[] tempList = file.list();   // 列出File对象的所有文件名，返回String数组
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {   // 已包含分隔符   就直接拼接
                temp = new File(path + tempList[i]);
            } else {                                // 否则，就就分隔符再拼接
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {   // File对象所对应的是否是文件
                temp.delete();
            }
            if (temp.isDirectory()) {  // File对象所对应的是否是目录，还有目录
                //  递归调用哦
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                //  调用delFolder（） 最后又是调用自己
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 重命名文件夹
     * @param oldPath
     * @param newPath
     */
    public static void rename(String oldPath, String newPath) {
        File booksIndexDirBak = new File(oldPath);
        File dest = new File(newPath);
        // 重命名此File对象所对应的文件或目录， 返回值为boolean
        if (!booksIndexDirBak.renameTo(dest)) {    // 死循环
            rename(oldPath, newPath);
        }
    }
}
