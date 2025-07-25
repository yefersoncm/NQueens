package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NQueensRandom {

    public static void main(String[] args) {
        int n = 8; // Change N to solve for different board sizes
        System.out.println("Finding a random solution for N = " + n + ":");
        solveNQueens(n);
    }

    public static void solveNQueens(int n) {
        char[][] board = new char[n][n];
        // Initialize the board with empty spaces
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        // Generate a random order of rows to try
        List<Integer> rowOrder = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rowOrder.add(i);
        }
        Collections.shuffle(rowOrder, new Random()); // Shuffle with a new Random instance

        if (solveNQueensUtil(board, 0, n, rowOrder)) {
            System.out.println("Solution found:");
            printBoard(board);
        } else {
            System.out.println("No solution found.");
        }
    }

    /**
     * Recursive utility function to solve the N-Queens problem with a random search order.
     *
     * @param board The current state of the chessboard.
     * @param col The current column we are trying to place a queen in.
     * @param n The size of the board (N).
     * @param rowOrder The shuffled list of row indices to try.
     * @return True if a solution is found, false otherwise.
     */
    private static boolean solveNQueensUtil(char[][] board, int col, int n, List<Integer> rowOrder) {
        // Base case: If all queens are placed, return true
        if (col >= n) {
            return true;
        }

        // Try placing the queen in rows according to the shuffled order
        for (int i : rowOrder) { // Iterate through the shuffled row order
            if (isSafe(board, i, col, n)) {
                // Place this queen in board[i][col]
                board[i][col] = 'Q';

                // Recur to place the rest of the queens
                if (solveNQueensUtil(board, col + 1, n, rowOrder)) { // Pass the same rowOrder
                    return true;
                }

                // If placing queen in board[i][col] doesn't lead to a solution,
                // then remove queen from board[i][col] (backtrack)
                board[i][col] = '.';
            }
        }
        // If queen cannot be placed in any row in this column, return false
        return false;
    }

    /**
     * Checks if it's safe to place a queen at board[row][col].
     * (This method remains the same as in the previous solution)
     *
     * @param board The current state of the chessboard.
     * @param row The row to check.
     * @param col The column to check.
     * @param n The size of the board (N).
     * @return True if it's safe to place a queen, false otherwise.
     */
    private static boolean isSafe(char[][] board, int row, int col, int n) {
        int i, j;

        // Check this row on left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 'Q') {
                return false;
            }
        }

        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < n; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    /**
     * Prints the chessboard.
     * (This method remains the same as in the previous solution)
     *
     * @param board The chessboard to print.
     */
    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}