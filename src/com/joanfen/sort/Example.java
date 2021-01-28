package com.joanfen.sort;

import edu.princeton.cs.algs4.StdIn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 排序算法类的模板
 * 排序成本模型：在研究排序算法时，我们需要计算【比较】和【交换】的数量。
 * 对于不交换元素的算法，我们会计算【访问数组的次数】
 * 是否原地排序：空间复杂度，需要额外使用多少空间
 * 选择排序，插入排序，希尔排序，归并排序，快速排序，堆排序
 */
public class Example {
    public static void sort(Comparable[] a) {

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 打印数组元素
     * @param a
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + "");
        }
        System.out.println();
    }

    /**
     * 测试数据元素是否有序
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            // 需要在 arguments 中添加参数
            FileInputStream input = new FileInputStream("src/com/joanfen/sort/" + args[0]);
            System.setIn(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
