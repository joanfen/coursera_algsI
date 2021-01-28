package com.joanfen.week1.homework;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int n;
    private final int trials;

    private double mean;
    private double std = Double.NaN;

    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("invalid n or trials: n and trails should > 0");
        }
        this.n = n;
        this.trials = trials;
        double[] ps = new double[trials];
        for (int i = 0; i < trials; i++) {
            ps[i] = simulation();
        }
        if (trials == 1) {
            mean = ps[0];
        } else {
            mean = StdStats.mean(ps);
            std = StdStats.stddev(ps);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return std;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - ((CONFIDENCE_95 * this.std) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + ((CONFIDENCE_95 * this.std) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        StdOut.print("please input grid size: ");
        int n = StdIn.readInt();
        StdOut.print("please input trials: ");
        int t = StdIn.readInt();
        PercolationStats s = new PercolationStats(n, t);
        StdOut.println("mean                    = " + s.mean());
        StdOut.println("stddev                  = " + s.stddev());
        StdOut.println("95% confidence interval = " + s.confidenceLo()
                + ", " + s.confidenceHi());
    }

    private double simulation() {
        Percolation percolation = new Percolation(n);
        int openCount = 0;
        while (!percolation.percolates()) {
            openSite(percolation);
            openCount++;
        }
        return (double) openCount / (n * n);
    }

    private void openSite(Percolation percolation) {
        boolean findOpenSite = true;
        int row = 0, col = 0;
        while (findOpenSite) {
            row = StdRandom.uniform(1, n + 1);
            col = StdRandom.uniform(1, n + 1);
            findOpenSite = percolation.isOpen(row, col);
        }
        percolation.open(row, col);
    }
}
