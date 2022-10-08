package implementation;

import java.util.Arrays;
import java.util.Scanner;

public class 원상_복구 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 카드의 갯수
        int K = scanner.nextInt(); // 섞은 횟수
        int[] result = new int[N]; // K번 카드를 섞은 후 배치
        int[] rotate = new int[N]; // 카드 섞는 정보

        for (int i = 0; i < result.length; i++) {
            result[i] = scanner.nextInt();
        }

        for (int i = 0; i < rotate.length; i++) {
            rotate[i] = scanner.nextInt();
        }

        int[] origin = new int[N]; // 최초 카드 배치

        while (K > 0) {
            // 카드 엮으로 섞기
            for (int i = 0; i < rotate.length; i++) {
                origin[rotate[i] - 1] = result[i];
            }
            result = Arrays.copyOf(origin, origin.length);
            K--;
        }

        for (int card : origin) {
            System.out.print(card + " ");
        }

        scanner.close();
    }

}
