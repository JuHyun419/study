package twopointer;

import java.util.Scanner;

public class 연속_부분수열2 {

    public static void main(String[] args) {
        연속_부분수열2 T = new 연속_부분수열2();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int m = kb.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = kb.nextInt();
        }
        System.out.print(T.solution(m, numbers));
    }

    private int solution(int m, int[] numbers) {
        int count = 0;
        int left = 0;
        int sum = 0;

        for (int number : numbers) {
            sum += number;

            // 연속부분수열의 합이 M보다 작을 때 까지 좌측 수열 빼기
            while (sum >= m) {
                if (sum == m) {
                    count++;
                }
                sum -= numbers[left++];
            }

        }
        return count;
    }

}
