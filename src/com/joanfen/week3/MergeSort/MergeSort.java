package com.joanfen.week3.MergeSort;

import com.joanfen.sort.Sort;

public class MergeSort extends Sort {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sortByBottomUp(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int size = 1; size < N; size = size + size) {
            for (int lo = 0; lo < N - size; lo += size + size) {
                merge(a, aux, lo, lo + size + 1, Math.min(lo + size + size - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{5, 9, 10, 2, 3};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
