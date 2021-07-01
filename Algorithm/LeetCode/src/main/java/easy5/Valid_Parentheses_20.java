package easy5;

import java.util.Stack;

public class Valid_Parentheses_20 {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (stack.isEmpty() || isOpenBrackets(ch)) {
                stack.push(ch);
                continue;
            }

            char lastChar = stack.peek();

            if (lastChar == '(' && ch == ')') {
                stack.pop();
            } else if (lastChar == '{' && ch == '}') {
                stack.pop();
            } else if (lastChar == '[' && ch == ']') {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    private static boolean isOpenBrackets(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    public static void main(String[] args) {
        String s = "()[{}]";
        System.out.println(isValid(s));
    }
}
