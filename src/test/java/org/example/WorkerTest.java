package org.example;

import org.example.annotation.FiledAnnotation;
import org.example.annotation.MethodAnnotation;
import org.example.annotation.TypeAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WorkerTest {

    public static void main(String[] args) throws Exception {
        Class aClass = Class.forName("org.example.work.Worker");
        Method[] methods = aClass.getMethods();
        // 判断Worker类中是否有TypeAnnotation注解
        boolean flag = aClass.isAnnotationPresent(TypeAnnotation.class);
        // 获取Worker类中TypeAnnotation注解值
        if (flag) {
            TypeAnnotation typeAnnotation = (TypeAnnotation) aClass.getAnnotation(TypeAnnotation.class);
            System.out.println("类名：" + aClass.getName());
            System.out.println("@TypeAnnotation值:" + typeAnnotation.value());
            System.out.println("------------------------------------------");
        }

        // 方法上注解
        List<Method> list = new ArrayList<>();
        for (int i=0; i<methods.length; i++) {
            list.add(methods[i]);
        }
        for (Method method : list) {
            MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
            if (methodAnnotation == null) {
                continue;
            }
            System.out.println("方法名称：" + method.getName());
            System.out.println("方法上注解name = " + methodAnnotation.name());
            System.out.println("方法上注解url = " + methodAnnotation.url());
            System.out.println("------------------------------------------");
        }

        // 属性上注解
        List<Field> fields = new ArrayList<>();
        for (Field field : aClass.getDeclaredFields()) { // 访问所有字段
            FiledAnnotation filedAnnotation = field.getAnnotation(FiledAnnotation.class);
            if (filedAnnotation == null) {
                continue;
            }
            System.out.println("属性名称：" + field.getName());
            System.out.println("属性注解值：" + filedAnnotation.value());

        }
    }
}
