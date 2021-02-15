package com.joanfen.week4.PriorityQueue;

import com.joanfen.sort.Sort;

public class UnOrderedMaxPQ<Key extends Comparable<Key>> extends Sort {
    private Key[] pq;
    private int n;

    public UnOrderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return  n == 0;
    }

    public int size() {
        return n;
    }
    public void insert(Key x) {
        pq[n++] = x;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (less(pq[max], pq[i])) max = i;
        }
        exch(pq, max, n-1);
        return pq[--n];
    }
}
