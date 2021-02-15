package com.joanfen.week3.QuickSort;

/**
 * 三分快排法
 * 为了解决大量重复值的问题
 * 使用 [lt, gt] 区间来存放所选取的基准值
 * 比基准值大的在 lt 左边，比其大的在 gt 右边
 */
public class ThreeWayQuickSort extends QuickSort {
    @Override
    public void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, i++, lt++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{5,  5, 5, 9, 9, 9, 10, 10, 2, 3};
        new ThreeWayQuickSort().sort(a);
        assert isSorted(a);
        show(a);
    }
}
