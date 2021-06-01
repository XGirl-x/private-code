package org.example.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.UUID;

/**
 * 随机字符串工具类
 */
public class RandomUtil {

    /**
     * 获取指定长度的随机字符串
     * @param mode 字符模式类型
     * @param strOutLength 输出的字符串长度
     * @return
     */
    public static String getRandomStr(int mode, int strOutLength){
        String str = "";
        switch (mode) {
            case 1:
                str = "1234567890";
                break;
            case 2:
                str = "abcdefghijklmnopqrstuvwxyz";
                break;
            case 3:
                str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;
            case 4:
                str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
                break;
            case 5:
                str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;
            case 6:
                str = "abcdefghijklmnopqrstuvwxyz1234567890";
                break;
            case 7:
                str = "123456789bcde";
                break;
            default:
                str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
                break;
        }

        int strLength = str.length();
        Random random = new Random();
        int min = 0;
        int max = strLength;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<strOutLength;i++){
            int index = random.nextInt(max - min) + min;
            String tempChar = str.substring(index, index+1);
            if(sb.toString().length()==strOutLength){
                break;
            }
            sb.append(tempChar);
        }

        return sb.toString();
    }

    /**
     *
     * @throws Exception
     */
    public static void createDevicePwd() throws Exception {
        String fileName = "D://device-pwd_"+DateUtils.getCurrentDateStr(DateUtils.DATE_TIMESTAMP_SEC_FMT)+".txt";
        /* 写入Txt文件 */
        File file = new File(fileName); // 相对路径，如果没有则要建立一个新的output。txt文件
        if(!file.exists()){
            // 创建新文件
            file.createNewFile();
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        for(int i=0;i<1000;i++){
            Thread.sleep(1000L);
            String randomCode = RandomUtil.getRandomStr(7,18);
            String initCode = DateUtils.getCurrentDateTimeStr(DateUtils.DATE_TIMESTAMP_SEC_FMT);
            String devicePwd = randomCode+initCode;
            System.out.println(devicePwd);
            out.write(devicePwd+"\r\n"); // \r\n即为换行
        }
        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件
        System.out.println("生成门禁机密钥完毕");
    }

    /**
     * 生成6位随机数
     * 1.适用于发送短信验证码
     * @return
     */
    public static String generateVerCode(){
        Random random = new Random();
        int x = random.nextInt(899999)+100000;
        return x+"";
    }

    /**
     * 生成4位随机数
     * 1.适用于发送短信验证码
     * @return
     */
    public static String generateCheckCode(){
        Random random = new Random();
        int x = random.nextInt(8999)+1000;
        return x+"";
    }

    /**
     * 生成6位数随机数
     * @return
     */
    public static String generatePassword(){
        Random random = new Random();
        int x = random.nextInt(899999)+100000;
        return x+"";
    }

    /**
     * 获取UUID
     * @return
     */
    public static String getRandomUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        return uuid;
    }

    public static void main(String[] args){
        System.out.println(generateCheckCode());
        System.out.println(RandomUtil.getRandomUUID());
    }
}

