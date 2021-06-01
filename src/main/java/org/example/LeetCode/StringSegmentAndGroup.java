package org.example.LeetCode;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 输入一个字符串（只含大小写字母，数字和"-"）和一个整数，除了开始到第一个"-"出现的位置的字符串不用改变，第一个"-"后面的字符串以"-"分割,再以整数个分割，
 * 如果这个分割后的字符串里大写字母比小写字母少，则转为小写字母，反之亦然，如果一样，则不转换
 * 最后把转换后的字符串重新整合成新的字符串，以"-"拼接
 * 示例：输入字符串：huU0s-HkUjoHJh8-I0089
 *      输入整数：3
 *      输出：huU0s-HKU-joh-Jh8-I00-89
 */
public class StringSegmentAndGroup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = 0;
        String s = null;
        System.out.println("请输入字符串：");
        if (in.hasNext()) {
            s = in.next();
            System.out.println("输入的字符串是：" + s);
        }
        System.out.println("请输入需要截取的长度：");
        if (in.hasNextInt()) {
            i = in.nextInt();
            System.out.println("输入的整数是：" + i);
        } else {
            System.out.println("输入的不是整数！");
        }
        int index = s.indexOf("-");
        System.out.println("第一次出现'-'的位置是：" + index);
        String s1 = s.substring(0,index);
        System.out.println("开始到第一个'-'出现的位置的字符串是：" + s1);
        String s2 = s.substring(index + 1);
        System.out.println("第一个'-'后面的字符串是：" + s2);

        StringSegmentAndGroup segmentAndGroup = new StringSegmentAndGroup();
        /** 开始分割并且转换 */
        // 把最后需要组合的处理过的子串放进list
        List<String> list = new ArrayList<>();
        // 第一个字符串不需要处理，直接放进list
        list.add(s1);
        // 对后面的字符串作处理，先分割，再对子串分割，再替换
        String[] strings = s2.split("-");
        for (String str : strings) {
            int length = str.length();
            // 判断字符串长度是否能整除切割长度
            // 0,3  3,6 6,7     j*i, (j+1)*i
            if (length%i != 0) {
                for (int j=0; j<length/i; j++) {
                    String s3 = str.substring(j*i, (j+1)*i);
                    // 转换
                    String news3 = segmentAndGroup.trans(s3);
                    System.out.println("分割并且转换后的子字符串：" + news3);
                    list.add(news3);
                }
                // length/i * i, length
                String s4 = str.substring(length/i * i, length);
                String news4 = segmentAndGroup.trans(s4);
                System.out.println("分割并且转换后的子字符串：" + news4);
                list.add(news4);
            } else {
                for (int m=0; m<length/i; m++) {
                    String s5 = str.substring(m*i, (m+1)*i);
                    String news5 = segmentAndGroup.trans(s5);
                    System.out.println("分割并且转换后的子字符串：" + news5);
                    list.add(news5);
                }
            }
        }

        // 对list中所有字符串进行拼接
        String newString = StringUtils.join(list, "-");
        System.out.println("最终的字符串为：" + newString);
        in.close();

    }

    /**
     * 判断字符串大小写字母个数并转换
     * @param str
     * @return
     */
    public String trans(String str) {
        int countA = 0;
        int counta = 0;
        for (int i=0; i<str.length(); i++) {
            // 大写 ASCII码值：65-90
            if ((byte)str.charAt(i)>64 && (byte)str.charAt(i)<91) {
                countA++;
            }
            // 小写 ASCII码值：97-122
            if ((byte)str.charAt(i)>96 && (byte)str.charAt(i)<123) {
                counta++;
            }
        }
        if (countA > counta) {
            str = str.toUpperCase();
        } else if (countA < counta) {
            str = str.toLowerCase();
        }
        return str;
    }
}
