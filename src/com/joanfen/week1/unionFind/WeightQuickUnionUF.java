package com.joanfen.week1.unionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class WeightQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;

    public WeightQuickUnionUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        sz = new int[n];
        Arrays.fill(sz, 1);
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != id[p]) { // 说明它已经被连接过了，下探
            p = id[p]; // 找到新的值，并赋值给 p，继续循环，一直找到根结点
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int proot = find(p);
        int qroot = find(q);
        if (proot == qroot) {
            return;
        }
        if (sz[proot] < sz[qroot]) {
            id[proot] = qroot;
            sz[qroot] += sz[proot];
        } else {
            id[qroot] = proot;
            sz[proot] += sz[qroot];
        }
        count--;
    }

    public static void main(String[] args) {
        try {
            FileInputStream input = new FileInputStream("src/com/joanfen/unionFind/" + args[0]);
            System.setIn(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int N = StdIn.readInt();
        WeightQuickUnionUF quickUnion = new WeightQuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            quickUnion.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(quickUnion.count() + " components");
    }

}
