package easy5;

import java.util.ArrayList;
import java.util.List;

public class Lucky_Numbers_in_a_Matrix_1380 {
    public static List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int minColumn = 0;

            // find minimum element in row
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minColumn = j;
                }
            }

            // find maximum element in column
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][minColumn] > max) {
                    max = matrix[j][minColumn];
                }
            }

            if (min == max) {
                list.add(min);
                break;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 8, 7}, {9, 16, 13}, {15, 16, 17}};

        luckyNumbers(matrix);
    }
}
