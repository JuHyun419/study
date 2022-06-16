package twopointer;

import java.util.Scanner;

public class 연속된_자연수의_합3 {

    /**
     * 수학적 풀이: O(n)
     *  15 - (1 + 2) / 2 의 나머지가 0이면 count + 1
     *  15 - (1 + 2 + 3) / 3 의 나머지가 0이면 count + 1
     *  15 - (1 + 2  + 3 + 4) / 4 의 나머지가 0이면 count + 1
     *  ...
     *  반복적으로 진행
     */
    private int solution(int n) {
        int result = 0;
        int count = 1; // 연속된 자연수의 갯수
        n--;

        while (n > 0) {
            count++;
            n -= count;
            if (n % count == 0) result++;
        }
        return result;
    }

    public static void main(String[] args){
        연속된_자연수의_합3 T = new 연속된_자연수의_합3();
        Scanner kb = new Scanner(System.in);
        int n=kb.nextInt();
        System.out.print(T.solution(n));
    }
}
