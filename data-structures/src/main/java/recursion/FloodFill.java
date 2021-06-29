package recursion;

import java.util.Scanner;

/**
 * << input >>
 * 5
 * 0 0 0 0 0
 * 0 0 0 1 1
 * 0 0 0 1 0
 * 1 1 1 1 0
 * 0 0 0 0 0
 * 1 1 (start)
 *
 * << output >>
 * 1 1 1 1 1
 * 1 1 1 1 1
 * 1 1 1 1 0
 * 1 1 1 1 0
 * 0 0 0 0 0
 */
public class FloodFill {
    static int N;
    static int[][] board = new int[100][100];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
        int startRow, startCol;
        startRow = scanner.nextInt();
        startCol = scanner.nextInt();

        fill(startRow, startCol);
        printBoard();

        scanner.close();
    }

    static void fill(int r, int c) {
        // Base case: out of bound
        if (r < 0 || r > N - 1 || c < 0 || c > N - 1) return;
        if (board[r][c] != 0) return;

        board[r][c] = 1;

        // Recursive case
        fill(r - 1, c); // up
        fill(r + 1, c); // down
        fill(r, c - 1); // left
        fill(r, c + 1); // right
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
