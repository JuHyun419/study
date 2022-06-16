package twopointer;

import java.util.Scanner;

public class 최대_매출 {

    // TLE - O(N*M)
    private int solution(int k, int[] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length - k; i++) {
            int compare = 0;
            for (int j = i; j < i + k; j++) {
                compare += arr[j];
            }
            sum = Math.max(sum, compare);
        }
        return sum;
    }

    public static void main(String[] args) {
        최대_매출 T = new 최대_매출();
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
