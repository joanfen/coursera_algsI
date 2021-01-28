package com.joanfen.week3.MergeSort.collinearPoints;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final Point[] points;
    private final ArrayList<LineSegment> lines = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        this.validate(points);
        this.points = points;
        int n = points.length;
        Arrays.sort(this.points);
        for (int k1 = 0; k1 < n; k1++) {
            Point p1 = this.points[k1]; // 第1个点
            for (int k2 = k1 + 1; k2 < n; k2++) {
                Point p2 = this.points[k2]; // 第2个点
                for (int k3 = k2 + 1; k3 < n; k3++) {
                    Point p3 = this.points[k3];  // 第3个点
                    double slope1 = p1.slopeTo(p2); // p1, p2 的斜率
                    double slope2 = p1.slopeTo(p3); // p1, p3 的斜率
                    if (slope1 == slope2)  { // 前两个斜率相等
                        for (int k4 = k3 + 1; k4 < n; k4++) {
                            Point p4 = this.points[k4];  // 第4个点
                            double slope3 = p1.slopeTo(p4); // p1, p4 的斜率
                            if (slope2 == slope3) {  // 和最后一个点的斜率相等
                                lines.add(new LineSegment(p1, p4));
                            }
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments()  {
        return lines.size();
    }
    public LineSegment[] segments() {
        return lines.toArray(new LineSegment[lines.size()]);
    }

    private void validate(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Error: The input points can't be null!");
        for (Point point : points) {
            if (point == null)  {
                throw new IllegalArgumentException("Error: There can't be null object in the points array!");
            }
        }
    }
}
