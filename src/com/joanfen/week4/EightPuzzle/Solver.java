package com.joanfen.week4.EightPuzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;

public class Solver {
    private MinPQ<SearchNode> minPQ;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        minPQ = new MinPQ<>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                return Integer.compare(o1.manhattan, o2.manhattan);
            }
        });
        minPQ.insert(new SearchNode(initial));
        solve();
    }

    public void solve() {
        SearchNode node = minPQ.delMin();
        for (Board board : node.board.neighbors()) {
            if (!(board.equals(node.board))) {
                SearchNode neighborNode = new SearchNode(node, node.moves + 1, board);
                minPQ.insert(neighborNode);
                if (board.isGoal()) {
                    return;
                }
            }
        }
        if (!isSolvable()) {
            solve();
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return minPQ.min().manhattan == 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable()) {
            return minPQ.min().moves;
        }
        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable()) {
            LinkedList<Board> boards = new LinkedList<Board>();
            SearchNode node = minPQ.min();
            boards.addFirst(node.board);
            while (node.previousNode != null) {
                node = node.previousNode;
                boards.addFirst(node.board);
            }
            return boards;
        }
        return null;
    }

    private class SearchNode {
        private SearchNode previousNode;
        private int moves;
        private Board board;
        private int manhattan;

        public SearchNode(Board board) {
            this.previousNode = null;
            this.moves = 0;
            this.board = board;
            this.manhattan = this.board.manhattan();
        }

        public SearchNode(SearchNode previousNode, int moves, Board board) {
            this.previousNode = previousNode;
            this.moves = moves;
            this.board = board;
            this.manhattan = this.board.manhattan();
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        try {
            // 在 run 的时候下面的文件夹路径写上 txt 的文件路径
            // 然后在参数中填上 txt 的文件名，如 tinyUF.txt，就可以读取到文件内容了
            String fileName = "puzzle04.txt";
            FileInputStream input = new FileInputStream("src/com/joanfen/week4/EightPuzzle/testCases/" + fileName);
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
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
