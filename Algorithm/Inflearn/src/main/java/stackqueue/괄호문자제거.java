package stackqueue;

import java.util.Scanner;
import java.util.Stack;

public class 괄호문자제거 {

    private String solution(String str) {
        return getAnswer(removeAllWordsBetweenBrackets(str));
    }

    private Stack<Character> removeAllWordsBetweenBrackets(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == ')') {
                while (stack.pop() != '(');
            } else {
                stack.push(ch);
            }
        }

        return stack;
    }

    private String getAnswer(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();

        for (Character character : stack) {
            sb.append(character);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        괄호문자제거 T = new 괄호문자제거();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(T.solution(str));
    }

}
