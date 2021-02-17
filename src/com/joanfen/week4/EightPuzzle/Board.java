package com.joanfen.week4.EightPuzzle;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private static final int[] DX = {-1, 0, 1, 0};
    private static final int[] DY = {0, -1, 0, 1};

    private final int[][] tiles;
    private final int n;

    private int hamming;
    private int manhattan;

    private int blankX;
    private int blankY;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException("board 不可为空");
        }
        n = tiles.length;
        if (n > 0 && tiles[0].length != n) {
            throw new IllegalArgumentException("board 的尺寸应为 n*n");
        }
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tile = tiles[i][j];

                this.tiles[i][j] = tile;
                if (tile == 0) {
                    blankX = i;
                    blankY = j;
                }

                if (inWrongPosition(tile, i, j)) {
                    hamming++;
                }
                manhattan += distanceToTarget(tile, i, j);
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(tiles[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this == y) {
            return true;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }
        Board board = (Board) y;
        return Arrays.deepEquals(board.tiles, this.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> list = new ArrayList<>();
        for (int i = 0; i < DX.length; i++) {
            // 上下左右四个方位的坐标
            int x = blankX + DX[i];
            int y = blankY + DY[i];
            if (inBoard(x, y)) { // 未超出边界
                int[][] newTiles = copyTiles(tiles);
                swap(newTiles, blankX, blankY, x, y);
                list.add(new Board(newTiles));
            }
        }
        return list;
    }

    private int[][] copyTiles(int[][] sourceTiles) {
        int[][] newTiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(sourceTiles[i], 0, newTiles[i], 0, n);
        }
        return newTiles;
    }

    // a board that is obtained by exchanging any pair of tiles
    // 为了验证无解的情况，将 puzzle 上的任意两个值直接交换顺序
    // 两个puzzle 一个有解，一个无解，由此来结束循环判断.
    public Board twin() {
        Board board = null;
        for (int i = 0; i < n * n - 1; i++) {
            int x1 = i / n, y1 = i % n;
            int x2 = (i + 1) / n, y2 = (i + 1) % n;
            if (tiles[x1][y1] != 0 && tiles[x2][y2] != 0) {
                int[][] newTiles = copyTiles(tiles);
                swap(newTiles, x1, y1, x2, y2);
                board = new Board(newTiles);
                break;
            }
        }
        return board;
    }

    /**
     * 方块不在其应在的位置上
     * 空白格(0)不算一个方块
     */
    private boolean inWrongPosition(int tile, int x, int y) {
        return tile != 0 && tile != x * n + y + 1;
    }

    /**
     * 方块与其目标位置的距离
     * 横向距离+纵向距离
     */
    private int distanceToTarget(int tile, int x, int y) {
        if (tile == 0) {
            return 0;
        }
        int targetX = (tile - 1) / n;
        int targetY = (tile - 1) % n;
        return Math.abs(targetX - x) + Math.abs(targetY - y);
    }

    private boolean inBoard(int x, int y) {
        return (x >= 0 && x < n) && (y >= 0 && y < n);
    }

    private void swap(int[][] theTiles, int x1, int y1, int x2, int y2) {
        int tmp = theTiles[x1][y1];
        theTiles[x1][y1] = theTiles[x2][y2];
        theTiles[x2][y2] = tmp;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        // 本地调试使用
//        try {
//            // 在 run 的时候下面的文件夹路径写上 txt 的文件路径
//            // 然后在参数中填上 txt 的文件名，如 tinyUF.txt，就可以读取到文件内容了
//            FileInputStream input = new FileInputStream("src/com/joanfen/week4/EightPuzzle/testCases/puzzle04.txt");
//            System.setIn(input);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        System.out.println("尺寸：" + initial.dimension());
        System.out.println("汉明距离：" + initial.hamming());
        System.out.println("曼哈顿距离：" + initial.manhattan());
        System.out.println("initial: " + initial.toString());
        System.out.println("twin: " + initial.twin().toString());

        System.out.println("neighbors：");
        initial.neighbors().forEach(board ->
                System.out.println(board.toString())
        );

    }
}
