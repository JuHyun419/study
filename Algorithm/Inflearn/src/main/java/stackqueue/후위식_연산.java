package stackqueue;

import java.util.Scanner;
import java.util.Stack;

public class 후위식_연산 {

    private static final int CHAR_TO_INT_ASCII_CODE = 48;

    public static void main(String[] args) {
        후위식_연산 T = new 후위식_연산();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(T.solution(str));
    }

    private int solution(String expr) {
        return postfix(expr);
    }

    private int postfix(String expr) {
        Stack<Integer> stack = new Stack<>();

        for (char ch : expr.toCharArray()) {
            if (Character.isDigit(ch)) {
                stack.push(ch - CHAR_TO_INT_ASCII_CODE);
            } else {
                int right = stack.pop();
                int left = stack.pop();

                stack.push(operate(left, right, ch));
            }
        }

        return stack.get(0);
    }

    private int operate(int left, int right, char operation) {
        int result;
        if (operation == '+') result = left + right;
        else if (operation == '-') result = left - right;
        else if (operation == '*') result = left * right;
        else if (operation == '/') result = left / right;
        else throw new IllegalStateException("'" + operation + "' operation is not supported");

        return result;
    }

}
