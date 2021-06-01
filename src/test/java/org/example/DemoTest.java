package org.example;

import org.example.aopTest.MathChild;
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
        list1.add(worker);
        list1.add(worker1);
        System.out.println(list1);
        
    }
}
