package org.example;

import org.apache.commons.lang3.StringUtils;
import org.example.aopTest.MathChild;
import org.example.utils.PageConvertUtils;
import org.example.work.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DemoTest {
    public static void main(String[] args) {
        new MathChild();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        // java8
        List<Integer> newList = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list);
        System.out.println(newList);

        List<Worker> list1 = new ArrayList<>();
        Worker worker = new Worker();
        worker.setMyInt(2);
        worker.setMyField("2222");

        Worker worker1 = new Worker();
        worker1.setMyInt(1);
        worker1.setMyField("666");

        Worker worker2 = new Worker();
        worker2.setMyInt(3);
        worker2.setMyField("888");

        list1.add(worker);
        list1.add(worker1);
        list1.add(worker2);
        for (Worker work : list1) {
            System.out.println(work.getMyInt() + ":" + work.getMyField());
        }
        System.out.println("-----------");
        List<Worker> list2 = PageConvertUtils.sortByField(list1, "myField");
        for (Worker work : list2) {
            System.out.println(work.getMyInt() + ":" + work.getMyField());
        }
        System.out.println("-----------");
        List<Worker> list3 = list2.subList(1,3);
        for (Worker work : list3) {
            System.out.println(work.getMyInt() + ":" + work.getMyField());
        }
        System.out.println("------------");

        String s = StringUtils.substringAfter("我是：你的小可爱", "我是：");
        System.out.println(s);

        String s1 = StringUtils.substringBeforeLast("我是你的小可爱吖哈哈哈哈吖", "吖");
        System.out.println(s1);


        
    }
}
