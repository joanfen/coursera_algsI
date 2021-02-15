package com.joanfen.week4.PriorityQueue;

import com.joanfen.sort.Sort;
import edu.princeton.cs.algs4.StdOut;

public class OrderedMaxPQ<Key extends Comparable<Key>> extends Sort {
    private Key[] pq;
    private int n;

    public OrderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key delMax() {
        return pq[--n];
    }

    public void insert(Key key) {
        int i = n - 1;
        // 移位
        while (i >= 0 && less(key, pq[i])) {
            pq[i + 1] = pq[i];
            i--;
        }
        pq[i + 1] = key;
        n++;
    }

    public static void main(String[] args) {
        OrderedMaxPQ<String> pq = new OrderedMaxPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }

}
