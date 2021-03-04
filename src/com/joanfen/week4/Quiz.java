package com.joanfen.week4;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Double.NaN;

public class Quiz {
    /**
     * 给定两个 double 值 a 和 b 以及对应的 Double 值 x 和 y
     * 自动 装箱 和 equals 方法
     *
     */
    public static void doubleCheck() {
//        double a = -0.0;
//        double b = 0.0;
        double a = NaN;
        double b = NaN;
        Double x = a;
        Double y = b;
        System.out.println(a==b);
        System.out.println(x.equals(y));

    }

    public static void stringCheck() {
        String a = "123";
        String b = new String("123");  // 这句话在内存中 创建了两个对象，一个在常量池中，一个在 堆内存 中
        System.out.println(a==b); // false == 比较的是内存地址
        System.out.println(a.equals(b)); // true 比较的值大小


    }

    public static void morris() {

    }

    public static void main(String[] args) {
        doubleCheck();
        stringCheck();
        Collection<Integer> collection = new ArrayList<>();
        Collection<Integer> collection1 = new ArrayList<>();
        collection.removeAll(collection1);
    }
}
