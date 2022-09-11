package implementation;

import java.util.Arrays;
import java.util.Scanner;

public class 전구 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 전구 갯수
        int M = scanner.nextInt(); // 명령어 갯수

        int[] bulbs = new int[N + 1];

        for (int i = 1; i < bulbs.length; i++) {
            bulbs[i] = scanner.nextInt();
        }

        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();

            if (a == 1) {
                changeBulbStatus(bulbs, b, c);
            } else if (a == 2) {
                reverseBulbStatus(bulbs, b, c);
            } else if (a == 3) {
                turnOffRange(bulbs, b, c);
            } else if (a == 4) {
                turnOnRange(bulbs, b, c);
            } else {
                throw new IllegalStateException(a + " 번 명령어는 존재하지 않습니다.");
            }
        }
        printStatus(bulbs);

        scanner.close();
    }

    private static void changeBulbStatus(int[] array, int index, int status) {
        array[index] = status;
    }

    private static void reverseBulbStatus(int[] array, int left, int right) {
        for (int i = left; i <= right; i++) {
            array[i] = (isOff(array[i])) ? 1 : 0;
        }
    }

    private static boolean isOff(int bulb) {
        return bulb == 0;
    }

    private static void turnOffRange(int[] array, int left, int right) {
        for (int i = left; i <= right; i++) {
            array[i] = 0;
        }
    }

    private static void turnOnRange(int[] array, int left, int right) {
        for (int i = left; i <= right; i++) {
            array[i] = 1;
        }
    }

    private static void printStatus(int[] array) {
        for (int i = 1; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
