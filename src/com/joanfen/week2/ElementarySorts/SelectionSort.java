package com.joanfen.week2.ElementarySorts;

import com.joanfen.sort.Sort;

/**
 * 选择排序
 * 1. 找到数组中最小的元素 A1
 * 2. 将A1 与数组的第一个元素交换位置
 * 3. 在剩下的数组中找到最小的元素A2
 * 4. 与数组的第二个元素交换位置
 * 5. 如此循环
 * N 次交换，约 N^2/2 次比较
 */
public class SelectionSort extends Sort {
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[minIndex])) minIndex = j;
            }
            exch(a, i, minIndex);
        }
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        Integer[] a = new Integer[]{5, 9, 10, 2, 3};
        selectionSort.sort(a);
        assert isSorted(a);
        show(a);
    }
}
