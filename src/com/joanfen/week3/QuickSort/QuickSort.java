package com.joanfen.week3.QuickSort;

import com.joanfen.sort.Sort;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort extends Sort {

    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while(i < j)  {
            while(less(a[++i], a[lo])) {
                if (i == hi) break;
            }
            while(less(a[lo],  a[--j])) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{5, 9, 5, 10, 2, 3, 9};
        new QuickSort().sort(a);
//        System.out.println(partition(a, 0, a.length - 1));
//        assert isSorted(a);
        show(a);
    }

}
