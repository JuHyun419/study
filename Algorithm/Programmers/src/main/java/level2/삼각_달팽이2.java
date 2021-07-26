package level2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 삼각_달팽이2 {
    public int[] solution(int n) {
        int[][] numbers = new int[n][n];
        int length = IntStream.rangeClosed(1, n).sum();
        int iterator = n;
        int number = 1;
        int x = 0;
        int y = 0;

        while (true) {
            // ↓︎
            for (int i = 0; i < iterator; i++) {
                numbers[x++][y] = number++;
            }
            if (isFinishedIterate(--iterator)) break;
            x--;

            // ➞
            for (int i = 0; i < iterator; i++) {
               numbers[x][++y] = number++;
            }
            if (isFinishedIterate(--iterator)) break;

            // ↖︎
            for (int i = 0; i < iterator; i++) {
                numbers[--x][--y] = number++;
            }
            if (isFinishedIterate(--iterator)) break;
            x++;
        }
        return twoDArrayToArray(numbers, length);
    }

    private boolean isFinishedIterate(final int iterate) {
        return iterate == 0;
    }

    private int[] twoDArrayToArray(int[][] arrays, int length) {
        int[] answer = new int[length];
        int index = 0;
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays.length; j++) {
                if (arrays[i][j] != 0) {
                    answer[index++] = arrays[i][j];
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        삼각_달팽이2 a = new 삼각_달팽이2();
        System.out.println(Arrays.toString(a.solution(4)));
    }
}
