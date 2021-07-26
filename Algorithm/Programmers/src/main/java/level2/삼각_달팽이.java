package level2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 삼각_달팽이 {

    public static int[] solution(int n) {
        int length = IntStream.rangeClosed(1, n).sum();
        int[][] arr = new int[n][n];
        int number = 1;
        int iterator = n;
        int x = 0;
        int y = 0;

        while (true) {
            // ↓
            for (int j = 0; j < iterator; j++) {
                arr[x++][y] = number++;
            }
            iterator--;
            if (iterator == 0) break;
            x --;

            // →
            for (int j = 0; j < iterator; j++) {
                arr[x][++y] = number++;
            }
            iterator--;
            if (iterator == 0) break;

            // ↖
            for (int j = 0; j < iterator; j++) {
                arr[--x][--y] = number++;
            }
            iterator--;
            if (iterator == 0) break;
            x ++;
        }
        return getAnswer(arr, length);
    }

    private static int[] getAnswer(int[][] arr, int length) {
        int[] answer = new int[length];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 0) continue;
                answer[index++] = arr[i][j];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] answer = solution(6);
        System.out.println(Arrays.toString(answer));
    }
}
