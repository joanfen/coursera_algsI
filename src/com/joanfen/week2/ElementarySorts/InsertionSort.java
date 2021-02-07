package com.joanfen.week2.ElementarySorts;

import com.joanfen.sort.Sort;

/**
 * 插入排序
 * 1. 1. 数组分为两部分，左边是排好序的，右边是未排序的
 * 2. 每次取未排序的第一个元素与排好序的部分进行比较
 * 3. 如果比排好序的元素小，则交换位置直至左边元素全部升序排列
 * N^2/4 次交换，约 N^2/4 次比较
 */
public class InsertionSort extends Sort {
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else break;
            }
        }
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        Integer[] a = new Integer[]{9, 5, 10, 2, 3};
        insertionSort.sort(a);
        assert isSorted(a);
        show(a);
    }
}
