package d2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 스도쿠_검증_1974 {

    private static final int SIZE = 9;

    static int[][] sudoku = {
            {7, 1, 4, 5, 8, 9, 2, 3, 6},
            {8, 5, 2, 3, 6, 4, 7, 1, 9},
            {3, 6, 9, 1, 7, 2, 8, 5, 4},
            {2, 3, 1, 9, 4, 6, 5, 7, 8},
            {6, 8, 5, 7, 3, 2, 9, 4, 1},
            {9, 4, 7, 8, 1, 5, 3, 6, 2},
            {1, 7, 8, 6, 9, 3, 4, 2, 5},
            {4, 2, 3, 1, 5, 8, 6, 9, 7},
            {5, 9, 6, 4, 2, 7, 1, 8, 3}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int[][] array = new int[SIZE][SIZE];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = scanner.nextInt();
                }
            }
            final int validSudoku = isValidSudoku(sudoku);
            System.out.println("#" + tc + " " + validSudoku);
        }
        scanner.close();
    }

    private static int isValidSudoku(int[][] array) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                List<Integer> sero = new ArrayList<>();
                List<Integer> garo = new ArrayList<>();
                List<Integer> grid = new ArrayList<>();

                if (i == 0) { // 세로 스도쿠 검증
                    for (int k = 0; k < SIZE; k++) {
                        if (sero.contains(array[k][i])) {
                            return 0;
                        }
                        sero.add(array[k][i]);
                    }
                }

                if (j == 0) { // 가로 스도쿠 검증
                    for (int k = 0; k < SIZE; k++) {
                        if (garo.contains(array[i][k])) {
                            return 0;
                        }
                        garo.add(array[i][k]);
                    }
                }

                if (i % 3 == 0 && j % 3 == 0) { // 3 x 3 스도쿠 검증
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            if (grid.contains(array[i + k][j + l])) {
                                return 0;
                            }
                            grid.add(array[i + k][j + l]);
                        }
                    }
                }
            }
        }
        return 1;
    }
}
