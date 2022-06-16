package twopointer;

import java.util.Scanner;

public class 최대_매출2 {

    private int solution(int k, int[] arr) {
        int sum = firstKDaysSum(k, arr);
        int answer = sum;

        for (int i = k; i < arr.length; i++) {
            // Sliding window
            sum += (arr[i] - arr[i - k]);
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    // 처음으로 연속된 K일간의 매출
    private int firstKDaysSum(int k, int[] arr) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        최대_매출2 T = new 최대_매출2();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int k = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.print(T.solution(k, arr));
    }

}
