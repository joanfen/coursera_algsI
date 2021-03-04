package com.joanfen.week5.KdTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private Node root;
    private int size;

    private class Node {
        private final Point2D point;
        private Node left;
        private Node right;
        private int level;
        /**
         * 每次构造node的同时，也构造出其对应的一个矩形
         * 如果是奇数层，则其rect为一条竖线，否则为一条横线
         * 这个变量是为了判断其与指定的rect是否相交，直接使用RectHV的 intersect 方法
         */
        private final RectHV rect;

        public Node(Point2D p) {
            if (p == null) {
                throw new IllegalArgumentException("point can not be null");
            }
            this.point = p;
            // the root corresponds to the unit square;
            this.rect = new RectHV(0, 0, 1, 1);
        }

        public Node(Point2D p, int level, RectHV rect) {
            if (p == null) {
                throw new IllegalArgumentException("point can not be null");
            }
            this.point = p;
            this.level = level;
            this.rect = rect;
        }

    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("point can not be null");
        }
        if (root == null) {
            root = new Node(p);
            size++;
        } else {
            insert(p, root);
        }

    }

    private void insert(Point2D p, Node node) {
        if (p == null) {
            throw new IllegalArgumentException("point can not be null");
        }
        int cmp = compare(p, node);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = generateNode(p, node);
                size++;
            } else {
                insert(p, node.left);
            }
        } else if (cmp > 0) {
            if (node.right == null) {
                node.right = generateNode(p, node);
                size++;
            } else {
                insert(p, node.right);
            }
        }
    }

    private Node generateNode(Point2D p, Node parent) {
        int cmp = compare(p, parent);
        if (cmp < 0) {
            if (parent.level % 2 == 0) {
                // 偶数层，比较结果是小于，说明是加在左边
                // 那么它的 xmin, ymin, ymax 都和父结点一样，xmax 设置为父结点的 p.x()
                return new Node(p, parent.level + 1, new RectHV(parent.rect.xmin(),  parent.rect.ymin(), parent.point.x(), parent.rect.ymax()));
            } else {
                // 奇数层，加在下边，那么只需要修改 ymax
                return new Node(p, parent.level + 1, new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.point.y()));
            }
        } else {
            if (parent.level % 2 == 0) {
                // 偶数层，加在右边，那么只需要修改 xmin
                return new Node(p, parent.level + 1, new RectHV(parent.point.x(),  parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax()));

            } else {
                // 奇数层，比较结果是大于，说明是加在上边，修改 ymin
                return new Node(p, parent.level + 1, new RectHV(parent.rect.xmin(),  parent.point.y(), parent.rect.xmax(), parent.rect.ymax()));

            }
        }
    }

    private int compare(Point2D p, Node node) {
        if (p == null || node == null) {
            throw new IllegalArgumentException("point/node can not be null");
        }
        int cmp;
        if (node.level % 2 == 0) {
            // 偶数层 x轴
            cmp = Point2D.X_ORDER.compare(p, node.point);
            if (cmp == 0) {
                cmp = Point2D.Y_ORDER.compare(p, node.point);
            }
        } else {
            // 奇数层 y轴
            cmp = Point2D.Y_ORDER.compare(p, node.point);
            if (cmp == 0) {
                cmp = Point2D.X_ORDER.compare(p, node.point);
            }
        }
        return cmp;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("point can not be null");
        }
        if (root == null) {
            return false;
        }
        return contains(p, root);
    }

    private boolean contains(Point2D p, Node node) {
        if (node == null) {
            return false;
        }
        if (p.equals(node.point)) {
            return true;
        }
        Node next = node.left;
        if (compare(p, node) > 0) {
            next = node.right;
        }
        return contains(p, next);
    }

    // draw all points to standard draw
    public void draw() {
        if (isEmpty()) {
            return;
        }
        StdDraw.clear();
        draw(root);
    }

    private void draw(Node node) {
        if (node != null) {
            StdDraw.setPenColor(Color.BLACK);
            node.point.draw();
            if (node.level % 2 == 0) {
                StdDraw.setPenColor(Color.DARK_GRAY);
                StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
            } else {
                StdDraw.setPenColor(Color.RED);
                StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
            }

            draw(node.left);
            draw(node.right);
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("rectangle can not be null");
        }
        if (isEmpty()) {
            return null;
        }
        return search(root, rect);
    }

    /**
     * 搜索 rect 包含的节点
     */
    private List<Point2D> search(Node node, RectHV rect) {
        List<Point2D> result = new ArrayList<>();
        if (node != null && rect.intersects(node.rect)) {
            result.addAll(search(node.left, rect));
            result.addAll(search(node.right, rect));
            if (rect.contains(node.point)) {
                result.add(node.point);
            }
        }
        return result;
    }
    private Point2D nearest;
    private double min;
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("point can not be null");
        }
        if (isEmpty()) {
            return null;
        }
        min = Double.POSITIVE_INFINITY;
        findNearest(p, root);
        return nearest;
    }

    private void findNearest(Point2D point, Node node) {
        if (node == null) {
            return;
        }
        if (node.rect.distanceSquaredTo(point) <= min) {
            double distance = node.point.distanceSquaredTo(point);
            if (distance < min) {
                min = distance;
                nearest = node.point;
            }
            if (node.left != null && node.left.rect.contains(point)) {
                findNearest(point, node.left);
                findNearest(point, node.right);
            } else if ((node.right != null && node.right.rect.contains(point))) {
                findNearest(point, node.right);
                findNearest(point, node.left);
            } else {
                double leftDistance = node.left == null ? Double.POSITIVE_INFINITY : node.left.rect.distanceSquaredTo(point);
                double rightDistance = node.right == null ? Double.POSITIVE_INFINITY : node.right.rect.distanceSquaredTo(point);
                if (leftDistance < rightDistance) {
                    findNearest(point, node.left);
                    findNearest(point, node.right);
                } else {
                    findNearest(point, node.right);
                    findNearest(point, node.left);
                }
            }
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
