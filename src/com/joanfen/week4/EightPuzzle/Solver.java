package com.joanfen.week4.EightPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Solver {
    private boolean isSolvable;
    private int moves;
    private LinkedList<Board> solutions;
    int operationCount;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("board can not be null");
        }
        MinPQ<SearchNode> minPQ = new MinPQ<>();
        minPQ.insert(new SearchNode(initial, false));
        minPQ.insert(new SearchNode(initial.twin(), true));
        solve(minPQ);
    }

    private void solve(MinPQ<SearchNode> minPQ) {
        // 循环搜索
        doSolve(minPQ);

        // 整理结果
        isSolvable = minPQ.min().isSolvable();
        moves = isSolvable ? minPQ.min().moves : -1;
        if (isSolvable) {
            solutions = new LinkedList<>();
            SearchNode node = minPQ.min();
            solutions.addFirst(node.board);
            while (node.previousNode != null) {
                node = node.previousNode;
                solutions.addFirst(node.board);
            }
        }
    }

    private void doSolve(MinPQ<SearchNode> pq) {
        while (!pq.min().board.isGoal()) {
            SearchNode node = pq.delMin();
            for (Board board : node.board.neighbors()) {
                if (node.previousNode == null || !(board.equals(node.previousNode.board))) {
                    SearchNode neighborNode = new SearchNode(node, board);
                    operationCount++;
                    pq.insert(neighborNode);
                    if (board.isGoal()) {
                        break;
                    }
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solutions;
    }

    private class SearchNode implements Comparable<SearchNode> {
        private final SearchNode previousNode;
        private final int moves;
        private final Board board;
        private final boolean isTwin;
        private final int manhattan;
        private final int priority;


        public SearchNode(Board board, boolean isTwin) {
            if (board == null) {
                throw new IllegalArgumentException("board can not be null");
            }
            this.previousNode = null;
            this.moves = 0;
            this.board = board;
            this.isTwin = isTwin;
            this.manhattan = this.board.manhattan();
            this.priority = this.manhattan + moves;
        }

        public SearchNode(SearchNode previousNode, Board board) {
            if (board == null) {
                throw new IllegalArgumentException("board can not be null");
            }
            this.board = board;
            this.previousNode = previousNode;
            this.moves = previousNode.moves + 1;
            this.isTwin = previousNode.isTwin;
            this.manhattan = board.manhattan();
            this.priority = this.manhattan + moves;
        }

        public boolean isSolvable() {
            return board.isGoal() && !isTwin;
        }

        @Override
        public int compareTo(SearchNode node) {
            return priority == node.priority
                    ? Integer.compare(manhattan, node.manhattan)
                    : Integer.compare(priority, node.priority);

        }
    }

    // test client (see below)
    public static void main(String[] args) {
//        // 本地调试使用
        try {
            // 在 run 的时候下面的文件夹路径写上 txt 的文件路径
            // 然后在参数中填上 txt 的文件名，如 tinyUF.txt，就可以读取到文件内容了
            //            String fileName = "puzzle04.txt";
            String fileName = "puzzle3x3-unsolvable1.txt";
//         String fileName = "puzzle2x2-unsolvable1.txt";
            FileInputStream input = new FileInputStream("src/com/joanfen/week4/EightPuzzle/testCases/" + fileName);
            System.setIn(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        In in = new In(args[0]); 提交作业时要使用他们提供的代码
        int n = StdIn.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = StdIn.readInt();
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        // 提交时总是报操作次数超限的错误，所以自己拿没过的 test 测试了一下，提交代码时不用带这个，多占空间
        System.out.println("操作次数:" + solver.operationCount);
    }
}
