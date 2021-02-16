package com.joanfen.week4.EightPuzzle;

import edu.princeton.cs.algs4.StdIn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Board {
    private final int[][] tiles;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        if (n > 0 && tiles[0].length != n) {
            throw new IllegalArgumentException("board 的尺寸应为 n*n");
        }
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, n);
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n);
        sb.append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(tiles[i][j]);
                if (j != n - 1) {
                    sb.append(" ");
                }
            }
            if (i != n - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return n * n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * n + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tile = tiles[i][j];
                int targetX = (tile - 1) / n;
                int targetY = (tile - 1) % n;
                if (tile != 0) {
                    count += Math.abs(targetX - i) + Math.abs(targetY - j);
                }
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0 && manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Board y) {
        if (dimension() != y.dimension()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Set<Board> set = new HashSet<>();
        int blankRow = 0, blankCol = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }

        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, -1, 0, 1};
        for (int i = 0; i < 4; i++) {
            // 上下左右四个方位的坐标
            int x = blankRow + dx[i];
            int y = blankCol + dy[i];
            if ((x >= 0 && x < n) && (y >= 0 && y < n)) {
                // 未超出边界
                Board board = new Board(tiles);
                // 与空白处的值进行交换
                board.tiles[blankRow][blankCol] = board.tiles[x][y];
                board.tiles[x][y] = 0;
                set.add(board);
            }
        }
        return set;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        // TODO: 有啥用呢这个方法？？？
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        try {
            // 在 run 的时候下面的文件夹路径写上 txt 的文件路径
            // 然后在参数中填上 txt 的文件名，如 tinyUF.txt，就可以读取到文件内容了
            FileInputStream input = new FileInputStream("src/com/joanfen/week4/EightPuzzle/testCases/puzzle01.txt");
            System.setIn(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = StdIn.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = StdIn.readInt();
        Board initial = new Board(tiles);
        System.out.println("尺寸：" + initial.dimension());
        System.out.println("汉明距离：" + initial.hamming());
        System.out.println("曼哈顿距离：" + initial.manhattan());
        System.out.println("neighbors：");
        initial.neighbors().forEach(board ->
                System.out.println(board.toString())
        );

    }
}
