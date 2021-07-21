package d3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class 외로운_문자_10912 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            String str = scanner.next();
            Stack<Character> stack = new Stack<>();
            for (char ch : str.toCharArray()) {
                if (stack.isEmpty()) {
                    stack.push(ch);
                    continue;
                }

                if (stack.contains(ch)) {
                    stack.remove((Object) ch);
                } else {
                    stack.push(ch);
                }
            }
            System.out.println("#" + tc + " " + getResult(stack));
        }
        scanner.close();
    }

    private static String getResult(Stack<Character> stack) {
        if (stack.size() == 0) {
            return "Good";
        }
        char[] result = new char[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            result[i] = stack.get(i);
        }
        Arrays.sort(result);
        return String.valueOf(result);
    }
}
