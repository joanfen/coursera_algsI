package com.joanfen.week3.MergeSort.collinearPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {
    private final Point[] points;
    private final ArrayList<LineSegment> lines = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {   // finds all line segments containing 4 or more points
        this.validate(points);
        int n = points.length;
        this.points = new Point[points.length];
        for (int i = 0; i < n; i++) {
            this.points[i] = points[i];
        }
        Arrays.sort(this.points);

        for (int i = 0; i < n; i++) {
            Point p = this.points[i];
            Point[] pointsOrderBySlope = this.points.clone();
            // 把 points 按 他们与点 p 的斜率大小来排序
            Arrays.sort(pointsOrderBySlope, p.slopeOrder());

            int j = 1;
            while (j < n) {
                LinkedList<Point> candidates = new LinkedList<>();
                Point pj = pointsOrderBySlope[j];
                double slope = p.slopeTo(pj);
                do {
                    // 斜率相等的放在数组里
                    candidates.add(pointsOrderBySlope[j++]);
                } while (j < n && p.slopeTo(pointsOrderBySlope[j]) == slope);

                // P 未包含在 candicate 数组中，所以是 >=3，如果不判断 p 是最小点的话会有重复的子线段
                if (candidates.size() >= 3 && p.compareTo(candidates.peek()) < 0) {
                    lines.add(new LineSegment(p, candidates.removeLast()));
                }
            }
        }
    }

    public int numberOfSegments() { // the number of line segments
        return lines.size();
    }

    public LineSegment[] segments() {  // the line segments
        return lines.toArray(new LineSegment[lines.size()]);
    }

    private void validate(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Error: The input points can't be null!");
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Error: There can't be null object in the points array!");
            }
        }
    }
}
