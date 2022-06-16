package twopointer;

import java.util.Scanner;

public class 연속_부분수열 {

    private int solution(int m, int[] arr) {
        int count = 0;
        int sum = 0; // left ~ right 까지의 합
        int left = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            if (sum == m) count++;

            // 1 1 1 1 5
            while (sum >= m) {
                sum -= arr[left++];
                if (sum == m) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        연속_부분수열 T = new 연속_부분수열();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int m = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.print(T.solution(m, arr));
    }

}
