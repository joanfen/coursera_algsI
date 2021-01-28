package com.joanfen.week1.unionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class UF {
    private int[] id; // 分量 id （以触点为索引）
    private int count;
    private int visitedCount;

    public UF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int visitedCount() {
        return visitedCount;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        visitedCount++;
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            boolean equal = id[i] == pID;
            visitedCount++;
            if (equal) {
                id[i] = qID;
                visitedCount++;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        try {
            FileInputStream input = new FileInputStream("src/com/joanfen/week1/unionFind/" + args[0]);
            System.setIn(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
            System.out.println("-----------------------------");
            StdOut.println(p + " " + q);
            StdOut.println("id[]: " + Arrays.toString(uf.id));
            StdOut.println("visitedCount: " + uf.visitedCount());

        }
        StdOut.println(uf.count() + " components");
    }

}
