package com.joanfen.week3.QuickSort.interview;

import com.joanfen.sort.Sort;

/**
 * 假设有 N 个螺丝和 N 个螺帽混在一堆，你需要快速将它们配对。
 * 一个螺丝只会匹配一个螺帽，一个螺帽也只会匹配一个螺丝。
 * 你可以试着把一个螺丝和一个螺帽拧在一起看看谁大了，但不能直接比较两个螺丝或者两个螺帽。
 * <p>
 * 最终的结果是 两组排序值一一对应，并不能保证排完是从小到大的，只是相对顺序
 */
public class NutsAndBolts extends Sort {

    public void sort(Comparable[] bolts, Comparable[] nuts) {
        sort(bolts, nuts, 0, Math.min(bolts.length - 1, nuts.length - 1));
    }


    public void sort(Comparable[] bolts, Comparable[] nuts, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(bolts, nuts[lo], lo, hi);
        partition(nuts, bolts[j], lo, hi);
        sort(nuts, bolts, lo, j - 1);
        sort(nuts, bolts, j + 1, hi);

    }

    public int partition(Comparable[] a, Comparable pivot, int lo, int hi) {
        int m = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], pivot) || less(pivot, a[i])) {
                m++;
                exch(a, i, m);
            } else {
                exch(a, i, lo);
                i--;
            }
        }

        exch(a, m, lo);

        return m;
    }

    public static void main(String[] args) {
        Integer[] bolts = new Integer[]{2, 5, 9, 3, 6, 10, 1, 8, 7, 3};

        Integer[] nuts = new Integer[]{6, 1, 7, 3, 2, 3, 5, 9, 8, 10};
        new NutsAndBolts().sort(bolts, nuts);
        show(bolts);
        show(nuts);
    }

}
