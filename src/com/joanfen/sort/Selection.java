package com.joanfen.sort;

import edu.princeton.cs.algs4.StdIn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 选择排序
 * 1. 找到数组中最小的元素 A1
 * 2. 将A1 与数组的第一个元素交换位置
 * 3. 在剩下的数组中找到最小的元素A2
 * 4. 与数组的第二个元素交换位置
 * 5. 如此循环
 * N 次交换，约 N^2/2 次比较
 */
public class Selection {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[minIndex])) minIndex = j;
            }
            exch(a, i, minIndex);
        }
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
