package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

public class PageConvertUtils {

    private static final Logger logger = LoggerFactory.getLogger(PageConvertUtils.class);

    /**
     * 传入指定的属性名称进行排序 排序规则 汉字=》 字母 =》 数字
     * @param list
     * @param column
     * @param <T>
     * @return
     */
    public static <T> List<T> sortByField(List<T> list, String column){
        try {
            List<T> notValueList = new ArrayList<>();
            Map<String,List<T>> nameMap = new HashMap<>(list.size());
            Set<String> valueList = new HashSet<>(list.size());
            for (T t : list) {
                Class<?> clazz = t.getClass();
                Field field = clazz.getDeclaredField(column);
                field.setAccessible(true);
                String value = (String) field.get(t);
                if(StringUtils.isEmpty(value)){
                    notValueList.add(t);
                    continue;
                }
                createOrUpadte(nameMap,value,t);
                valueList.add(value);
            }
            // 排序名称
            List<String> sortList = sortName(valueList);
            List<T> result = new ArrayList<>();
            for (String name : sortList) {
                List<T> ts = nameMap.get(name);
                if(!CollectionUtils.isEmpty(ts)){
                    result.addAll(ts);
                }
            }
            result.addAll(notValueList);
            return result;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("对字段 {} 排序失败 {}",column,e);
        }
        return list;
    }

    private static <T> void createOrUpadte(Map<String,List<T>> nameMap,String key,T t){
        List<T> result = nameMap.get(key);
        if(CollectionUtils.isEmpty(result)){
            result = new ArrayList<>();
        }
        result.add(t);
        nameMap.put(key,result);
    }

    private static List<String> sortName(Set<String> nameList){
        List<String> result = new ArrayList<>(nameList);
        //先拿所有以数字开头
        List<String> nameStartNum = nameList.stream()
                .filter(name -> name.matches("^[0-9].*")).sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        // 取5位长度
                        String m1 = o1.length()>5 ? o1.substring(0,5) : o1;
                        String m2 = o2.length()>5 ? o2.substring(0,5) : o2;
                        return Integer.parseInt(m1.replaceAll("[^0-9]*", "")) - Integer.parseInt(m2.replaceAll("[^0-9]*", ""));
                    }
                }).collect(Collectors.toList());
        //再拿所有以字母开头的小区
        List<String> nameStartAlph = nameList.stream()
                .filter(name -> name.matches("^[a-zA-Z].*")).sorted()
                .collect(Collectors.toList());
        //先把字母的拿出来，在放回去，此时变为1、数字， 3、汉字 2、字母，
        if (!CollectionUtils.isEmpty(nameStartAlph)){
            result.removeAll(nameStartAlph);
        }
        //再把数字的拿出来再放回去 3、汉字 2、字母 1、数字，
        if (!CollectionUtils.isEmpty(nameStartNum)){
            result.removeAll(nameStartNum);
        }
        // 中文排序
        Collator comparator = Collator.getInstance(java.util.Locale.CHINA);
        result.sort(comparator);
        result.addAll(nameStartAlph);
        result.addAll(nameStartNum);
        return result;
    }
}
