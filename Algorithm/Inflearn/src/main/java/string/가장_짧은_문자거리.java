package string;

import java.util.Scanner;

public class 가장_짧은_문자거리 {

    // left -> right, right -> left, 최솟값 비교
    public int[] solution(String s, char t) {
        int length = 101;
        int[] answer = new int[s.length()];

        // left to right
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t) {
                answer[i] = 0;
                length = 0;
            } else {
                length++;
                answer[i] = length;
            }
        }

        length = 101;

        // right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == t) {
                length = 0;
            } else {
                length++;
                answer[i] = Math.min(answer[i], length);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int input1 = in.nextInt();
        int input2 = in.nextInt();
        System.out.println(input1 + input2);
        return;
    }

}
