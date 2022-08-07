package stackqueue;

import java.util.Scanner;
import java.util.Stack;

public class 올바른_괄호 {

    private String solution(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
                continue;
            }

            if (ch == ')') {
                if (stack.isEmpty()) return "NO";

                if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }

    private String solution2(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '(') stack.push(ch);
            else {
                if (stack.isEmpty()) {
                    return "NO";
                }
                // ()) 와 같이 )가 많을 경우 -> isEmpty()에서 걸러지기 때문에 무조건 pop해도 된다.
                stack.pop();
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) {
        올바른_괄호 T = new 올바른_괄호();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(T.solution(str));
    }

}
