package sunwin.zhangdong.web.comm;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;
import sunwin.zhangdong.comm.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by wangjs on 2016/11/9.
 */
@Component
public class HandlerPhoto {
    /**
     * 如果有上传文件，保存文件 保存到指定目录
     * @param request
     * @param parentName
     * @throws Exception
     */
    public String handlerPhoto(HttpServletRequest request,String parentName) throws  Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("imgFile");
        String photoName=null;
        // 获得文件名：
        String filename = file.getOriginalFilename();
        if (StringUtil.isNotEmpty(filename)) {
            //保存为临时图片
            String imagePath = request.getServletContext().getRealPath("photo")+"/"+parentName;
            photoName=savePhoto(file,imagePath,parentName);
        }
        return photoName;
    }
    /**
     * 将文件保存到服务器里面
     * 返回文件名
     */
    public String saveVideoFile(HttpServletRequest request) throws  Exception {
        // 强制类型转换
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("imgFile");
        String videoName=null;
        // 获得文件名：
        String filename = file.getOriginalFilename();
        if (StringUtil.isNotEmpty(filename)) {
            String dir=request.getServletContext().getRealPath("video");
            FileHelper.mkdir(dir);
            String newFileName=UUID.randomUUID().toString();//生成新的文件名
            //生成新的文件名 并创建文件
            FileHelper fileHelper=new FileHelper();
            String extname=fileHelper.getExtensionName(file.getOriginalFilename());//获取后缀
            videoName=newFileName+"."+extname;
            fileHelper.createNewFile(dir+"/"+videoName);   // 创建文件
            //保存文件
            OutputStream out=new FileOutputStream(dir+"/"+videoName);  // 文件输入流
            InputStream is=file.getInputStream();   // 读取流，读取客户端的文件数据到内存
            int i = is.available(); // 得到文件大小
            byte data[] = new byte[i];
            is.read(data); // 读数据     读到内存中
            is.close();
            out.write(data); // 输出数据
            out.flush();
            out.close();
        }
        return videoName;
    }
    //保存文件 并返回文件名
    private String savePhoto(MultipartFile file, String imagePath,String parentName) throws  Exception{
        //生成新的文件名 并创建文件
        FileHelper fileHelper=new FileHelper();
        //判断文件夹是否存在 不存在则创建
        fileHelper.mkdir(imagePath);
        String newfilename=parentName+System.currentTimeMillis()+"";
        String extname=fileHelper.getExtensionName(file.getOriginalFilename());
        String filename=imagePath+"/"+newfilename+"."+extname;
        fileHelper.createNewFile(imagePath+"/"+newfilename+"."+extname);
        //保存文件
        OutputStream out=new FileOutputStream(filename);
        InputStream is=file.getInputStream();
        int i = is.available(); // 得到文件大小
        byte data[] = new byte[i];
        is.read(data); // 读数据
        is.close();
        out.write(data); // 输出数据
        out.flush();
        out.close();
        return "photo/"+parentName+"/"+newfilename+"."+extname;
    }


    /**
     * 读取图片到客户端
     * @param response
     * @param photoPath
     * @throws Exception
     */
    public void readPhoto(HttpServletResponse response,String photoPath) throws Exception{
        if(FileHelper.existFile(photoPath)){
            FileInputStream is = new FileInputStream(photoPath);
            int i = is.available(); // 得到文件大小
            byte data[] = new byte[i];
            is.read(data); // 读数据
            is.close();
            response.setContentType("image/*"); // 设置返回的文件类型
            OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
            toClient.write(data); // 输出数据
            toClient.close();
        }
    }

    //base64字符串转化成图片    创建图片文件   将base64转码后的图片信息写到创建的图片文件中      返回文件的全路径
    public String GenerateImage(HttpServletRequest request,String imgStr,String parentName) throws Exception{
        FileHelper fileHelper=new FileHelper();
        //对字节数组字符串进行Base64解码并生成图片
        String imagePath = "C:\\Users\\Administrator\\Desktop\\life\\sunwin.zhangdong.web\\src\\main\\webapp\\photo";
        fileHelper.mkdir(imagePath);    // 创建目录      是在远程机器上创建的吧！
        System.out.println(imagePath);
        // 不加也是String类型哦， 不用 + ""    文件名为：父文件夹+随机数
        String newfilename=parentName+System.currentTimeMillis()+"";
        String filePath=imagePath+"/"+newfilename+".jpg";   // 拼接文件路径
        System.out.println(filePath);
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64解码
        imgStr=imgStr.substring(imgStr.indexOf("base64,")+7);  //从指定的位置开始截取字符串
        byte[] b = decoder.decodeBuffer(imgStr);      // base64编码解码  结果保存到byte数组中
        for(int i=0;i<b.length;++i)                   // 处理异常数据
        {
            if(b[i]<0)        // 转码后的字节数组中如果有小于0的，做处理
            {//调整异常数据
                b[i]+=256;
            }
        }
        //生成jpeg图片
        fileHelper.createNewFile(filePath);    //创建文件    图片文件的全路径名，包括后缀哦！
        OutputStream out = new FileOutputStream(filePath);  // 字节输出流
        out.write(b);   // 将字节数组写入到文件输出流中     write(byte[]/char[] buf)

        // 关闭流，保证流的物理资源被回收，还可以将输出流缓冲区中的数据flush到物理节点中
        out.flush();
        out.close();          // close()必须要有，可以不用flush(),因为close()方法执行前会自动执行flush操作。
        imgStr=null;          // 引用置空
        System.out.println("photo/"+parentName+"/"+newfilename+".jpg");
        return newfilename+".jpg";
    }
}
