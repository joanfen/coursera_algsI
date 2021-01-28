package com.joanfen.week1.homework;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private final int n; // number of sites
    private final int vTop;
    private final int vBottom;
    private int[] openSites;

    private int openCount;

    private final WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be greater than zero!!!");
        }
        this.n = n;
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.vTop = 0;
        this.vBottom = n * n + 1;
        this.openSites = new int[n * n + 1];

        if (n > 1) {
            for (int k = 1; k <= n; k++) {
                uf.union(vTop, getIndex(1, k));
                uf.union(vBottom, getIndex(n, k));
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // open
        if (isOpen(row, col)) {
            return;
        }
        int currentPoint = getIndex(row, col);
        openSites[currentPoint] = 1;
        openCount++;

        unionAround(row - 1, col, currentPoint);
        unionAround(row + 1, col, currentPoint);
        unionAround(row, col + 1, currentPoint);
        unionAround(row, col - 1, currentPoint);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int idx = getIndex(row, col);
        return openSites[idx] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return isOpen(row, col) && uf.find(vTop) == uf.find(getIndex(row, col));
    }

    private int getIndex(int row, int col) {
        if (row > n || row <= 0) {
            throw new IllegalArgumentException("row index out of bounds");
        }
        if (col > n || col <= 0) {
            throw new IllegalArgumentException("col index out of bounds");
        }
        return (row - 1) * this.n + col;
    }

    private int getIndexSafely(int row, int col) {
        if (row > n || row <= 0 || col > n || col <= 0) {
            return -1;
        }
        return (row - 1) * this.n + col;
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(vTop) == uf.find(vBottom);
    }

    private void unionAround(int row, int col, int currentPoint) {
        int point = getIndexSafely(row, col);
        if (point != -1 && isOpen(row, col)) {
            uf.union(currentPoint, point);
        }
    }

    public static void main(String[] args) {
        StdOut.print("please input grid size: ");
        int n = StdIn.readInt();

        Percolation perc = new Percolation(n);
        do {
            StdOut.println();
            StdOut.println("Choose point to be opened: ");
            StdOut.print("please input x: ");
            int i = StdIn.readInt();

            StdOut.print("please input y: ");
            int j = StdIn.readInt();

            if (perc.getIndexSafely(i, j) == -1) {
                StdOut.println("Illegal input x, y !!!");
                continue;
            }
            perc.open(i, j);
        } while (!perc.percolates());

    }

}
