package org.example.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Base64Utils {


    private static Logger logger = LoggerFactory.getLogger(Base64Utils.class);


    /**
     * 本地图片转换成base64字符串
     * @param imgFile	图片本地路径
     * @return
     *
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:40:46
     */
    public static String imageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }



    /**
     * 在线图片转换成base64字符串
     *
     * @param imgURL	图片线上路径
     * @return
     *
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:43:18
     */
    public static String imageToBase64ByOnline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }


    /**
     * base64字符串转换成图片
     * @param imgStr		base64字符串
     * @param imgFilePath	图片存放路径
     * @return
     *
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:42:17
     */
    public static boolean base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

        if (StringUtils.isEmpty(imgStr)) // 图像数据为空
            return false;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 主要用于登录部分账号密码的base64字符串的解码
     * @param base64
     * @return
     */
    public static String base64ToStr(String base64){
        try {
            return new String(java.util.Base64.getDecoder().decode(base64),"UTF-8");
        }catch (UnsupportedEncodingException e){
            logger.error("base64ToStr 解码失败,UnsupportedEncoding");
            return null;
        }catch (Exception e){
            logger.error("base64ToStr 解码失败");
            return null;
        }
    }

    public static void main(String[] args) throws Exception {

//        //本地图片地址
//        String url = "D:/image/20180719134447.jpg";
//        //在线图片地址
//        String string = "http://bpic.588ku.com//element_origin_min_pic/17/03/03/7bf4480888f35addcf2ce942701c728a.jpg";
//
//        String str = Base64Utils.imageToBase64ByLocal(url);
//
//        //String ste = Base64Utils.ImageToBase64ByOnline(string);
//
//        System.out.println(str);
//
//        Base64Utils.base64ToImage(str,"D:/usr/local/wxcode/test1.jpg");
//
//        //Base64Utils.Base64ToImage(ste, "C:/Users/Administrator/Desktop/test2.jpg");

        String paw="abcdefg";
        String s =java.util.Base64.getEncoder().encodeToString(paw.getBytes());
        System.out.println(s);
        System.out.println(base64ToStr(s));
    }
}

