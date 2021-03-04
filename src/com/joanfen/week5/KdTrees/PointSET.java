package com.joanfen.week5.KdTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> treeSet;

    // construct an empty set of points
    public PointSET() {
          treeSet = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return treeSet.isEmpty();
    }

    // number of points in the set
    public int size() {
        return treeSet.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p ==  null) {
            throw new IllegalArgumentException("point can not be null");
        }
        if (!treeSet.contains(p)) {
            treeSet.add(p);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p ==  null) {
            throw new IllegalArgumentException("point can not be null");
        }
        return treeSet.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : treeSet) {
            point.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("rectangle can not be null");
        }
        List<Point2D> result = new ArrayList<>();
        for (Point2D point : treeSet) {
            if (rect.contains(point)) {
                result.add(point);
            }
        }
        return result;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p ==  null) {
            throw new IllegalArgumentException("point can not be null");
        }
        double min = Double.NEGATIVE_INFINITY;
        Point2D result = null;
        for (Point2D point : treeSet) {
            double distance = point.distanceSquaredTo(p);
            if (distance < min) {
                min = distance;
                result = point;
            }
        }
        return result;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET set = new PointSET();
        int n = 10;
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            set.insert(new Point2D(x, y));
            StdOut.printf("%8.6f %8.6f\n", x, y);
        }
        set.draw();
    }
}
