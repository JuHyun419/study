package d3;

import java.util.Scanner;
import java.util.Stack;

public class 제로_8931 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int T = scanner.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            final int K = scanner.nextInt();
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < K; i++) {
                int number = scanner.nextInt();
                if (number == 0) {
                    stack.pop();
                } else {
                    stack.push(number);
                }
            }
            System.out.println("#" + tc + " " + getSum(stack));
        }
        scanner.close();
    }

    private static int getSum(Stack<Integer> stack) {
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
