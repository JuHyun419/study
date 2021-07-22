package devmatching;

import java.util.Arrays;

// TODO: 시계방향 회전
public class 행렬_테두리_회전하기 {
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] matrix = getMatrix(rows, columns);

        for (int index = 0; index < queries.length; index++) {
            // (2,2) ~ (5,4)
            final int x1 = queries[index][0] - 1; // 1
            final int y1 = queries[index][1] - 1; // 1
            final int x2 = queries[index][2] - 1; // 4
            final int y2 = queries[index][3] - 1; // 3
            int temp = matrix[x1][y1]; // 가장 첫번째 위치하는 값 -> 한 칸 오른쪽으로 회전
            int min = temp;

            // ↑ 왼쪽
            for (int i = x1; i < x2; i++) {
                matrix[i][y1] = matrix[i + 1][y1];
                min = Math.min(min, matrix[i][y1]);
            }
            // ← 아래
            for (int i = y1; i < y2; i++) {
                matrix[x2][i] = matrix[x2][i + 1];
                min = Math.min(min, matrix[x2][i]);
            }
            // ↓ 오른쪽
            for (int i = x2; i > x1; i--) {
                matrix[i][y2] = matrix[i - 1][y2];
                min = Math.min(min, matrix[i][y2]);
            }
            // → 위
            for (int i = y2; i > y1; i--) {
                matrix[x1][i] = matrix[x1][i - 1];
                min = Math.min(min, matrix[x1][i]);
            }
            matrix[x1][y1 + 1] = temp;
            answer[index] = min;
        }
        return answer;
    }

    private static int[][] getMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        int value = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int rows = 3;
        int columns = 3;
        int[][] queries = {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}};
        int[] answer = solution(rows, columns, queries);
        System.out.println(Arrays.toString(answer));
    }
}
