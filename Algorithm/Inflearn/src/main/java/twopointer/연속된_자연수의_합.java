package twopointer;

import java.util.Scanner;

public class 연속된_자연수의_합 {

    // O(n^2)
    private int solution(int n) {
        int count = 0;
        for (int i = 1; i <= n / 2; i++) {
            int sum = i;
            for (int j = i + 1; ; j++) {
                sum += j;
                if (sum == n) {
                    count++;
                    break;
                }
                if (sum > n) break;
            }
        }
        return count;
    }

    public static void main(String[] args){
        연속된_자연수의_합 T = new 연속된_자연수의_합();
        Scanner kb = new Scanner(System.in);
        int n=kb.nextInt();
        System.out.print(T.solution(n));
    }

}
