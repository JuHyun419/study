package twopointer;

import java.util.Scanner;

public class 연속된_자연수의_합2 {

    // Two Pointers
    private int solution(int n) {
        int left = 1;
        int right = 1;
        int sum = 0;
        int count = 0;

        while (left <= n / 2) {
            sum += right++;
            if (sum == n) count++;

            while (sum >= n) {
                sum -= left++;
                if (sum == n) count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        연속된_자연수의_합2 T = new 연속된_자연수의_합2();
        Scanner kb = new Scanner(System.in);
        int n=kb.nextInt();
        System.out.print(T.solution(n));
    }
}
