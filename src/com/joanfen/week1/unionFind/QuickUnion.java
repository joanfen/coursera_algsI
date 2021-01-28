package com.joanfen.week1.unionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class QuickUnion {
    private int[] id;
    private int count;

    public QuickUnion(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
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
        id[proot] = qroot;
        count--;
    }

    public static void main(String[] args) {
        try {
            // 在 run 的时候下面的文件夹路径写上 txt 的文件路径
            // 然后在参数中填上 txt 的文件名，如 tinyUF.txt，就可以读取到文件内容了
            FileInputStream input = new FileInputStream("src/com/joanfen/week1/unionFind/" + args[0]);
            System.setIn(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int N = StdIn.readInt();
        QuickUnion quickUnion = new QuickUnion(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            quickUnion.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(quickUnion.count() + " components");
    }

}
