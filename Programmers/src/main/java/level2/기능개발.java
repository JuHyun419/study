package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class 기능개발 {
    private static final int PROCESS = 100;

    public static int[] solution(final int[] progresses, final int[] speeds) {
        final List<Integer> list = new ArrayList<>();
        final int length = progresses.length;
        final Stack<Integer> stack = new Stack<>();

        for (int i = length - 1; i >= 0; i--) {
            final int days = (int) Math.ceil((double)(PROCESS - progresses[i]) / speeds[i]);
            stack.push(days);
        }

        while (!stack.isEmpty()) {
            int deployCount = 1;
            final int top = stack.pop();
            while (!stack.isEmpty() && stack.peek() <= top) {
                deployCount ++;
                stack.pop();
            }
            list.add(deployCount);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(final String[] args) {
        final int[] progresses = {95, 90, 99, 99, 80, 99};
        final int[] speeds = {1, 1, 1, 1, 1, 1};

        System.out.println(Arrays.toString(solution(progresses, speeds)));
    }
}
