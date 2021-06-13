package easy4;

import java.util.Stack;

public class Make_The_String_Great_1544 {
    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (stack.isEmpty()) {
                stack.push(chars[i]);
            } else {
                char ch1 = stack.peek();
                char ch2 = chars[i];
                if (Character.toLowerCase(ch1) == Character.toLowerCase(ch2) && ch1 != ch2) {
                    stack.pop();
                } else {
                    stack.push(ch2);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
