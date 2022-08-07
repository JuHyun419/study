package stackqueue;

import java.util.Scanner;
import java.util.Stack;

public class 쇠막대기 {

    public static void main(String[] args) {
        쇠막대기 T = new 쇠막대기();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(T.solution(str));
    }

    private int solution(String str) {
        Stack<Character> stack = new Stack<>();
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push('(');
                continue;
            }

            stack.pop();
            count += (str.charAt(i - 1) == '(')
                    ? stack.size() //
                    : 1; // 쇠막대기 끝점
        }
        return count;
    }

}
