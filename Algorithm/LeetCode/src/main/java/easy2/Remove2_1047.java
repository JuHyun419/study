package easy2;

import java.util.Iterator;
import java.util.Stack;

public class Remove2_1047 {
    public static String removeDuplicates(final String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }

            if (stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        return charToString(stack);
    }

    private static String charToString(Stack<Character> stack) {
        Iterator<Character> iter = stack.iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            sb.append(iter.next().toString());
        }
        return sb.toString();
    }
}
