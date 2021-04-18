package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class 기능개발 {
    private static final int PROCESS = 100;

    public static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        int length = progresses.length;
        Stack<Integer> stack = new Stack<>();

        for (int i = length - 1; i >= 0; i--) {
            int days = (PROCESS - progresses[i]) / speeds[i];
            if ((PROCESS - progresses[i]) % speeds[i] != 0) {
                days += 1;
            }
            stack.push(days);
        }

        while (!stack.isEmpty()) {
            int deployCount = 1;
            int top = stack.pop();

            while (!stack.isEmpty() && stack.peek() <= top) {
                deployCount ++;
                stack.pop();
            }
            list.add(deployCount);

        }

        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static void main(String[] args) {
        int[] progresses = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};

        System.out.println(Arrays.toString(solution(progresses, speeds)));
    }
}
