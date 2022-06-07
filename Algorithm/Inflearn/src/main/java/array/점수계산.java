package array;

import java.util.Scanner;

public class 점수계산 {

    public int solution(int[] arr) {
        int sum = 0;
        int count = 0;

        for (int score : arr) {
            if (score == 1) {
                count++;
                sum += count;
            } else {
                count = 0;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        점수계산 T = new 점수계산();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.print(T.solution(arr));
    }
}
