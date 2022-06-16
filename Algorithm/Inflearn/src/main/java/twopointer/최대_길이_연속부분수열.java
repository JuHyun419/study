package twopointer;

import java.util.Scanner;

public class 최대_길이_연속부분수열 {

    private int solution(int k, int[] arr) {
        int left = 0;
        int count = 0;
        int max = 0;

        for (int right = 0; right < arr.length; right++) {
            // 0 -> 1로 무조건 변경
            if (arr[right] == 0) count++;

            // 0 -> 1을 최대 k번 변경했을 경우: left가 이동하면서 1 -> 0으로 롤백
            while (count > k) {
                if (arr[left] == 0) count--;
                left++;
            }
            max = Math.max(max, (right - left + 1));
        }
        return max;
    }

    public static void main(String[] args) {
        최대_길이_연속부분수열 T = new 최대_길이_연속부분수열();
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
