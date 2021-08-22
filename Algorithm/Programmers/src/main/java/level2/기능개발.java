package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class 기능개발 {
    private static final int PROCESS = 100;

    public static int[] solution(final int[] progresses, final int[] speeds) {
        List<Integer> features = new ArrayList<>();
        final Stack<Integer> workDays = getWorkDays(progresses, speeds);

        while (!workDays.isEmpty()) {
            int deployCount = 1;
            final int top = workDays.pop();
            while (!workDays.isEmpty() && workDays.peek() <= top) {
                deployCount ++;
                workDays.pop();
            }
            features.add(deployCount);
        }
        return listToArray(features);
    }

    private static Stack<Integer> getWorkDays(int[] progresses, int[] speeds) {
        Stack<Integer> workDays = new Stack<>();
        for (int i = progresses.length - 1; i >= 0; i--) {
            final int days = (int) Math.ceil((double)(PROCESS - progresses[i]) / speeds[i]);
            workDays.push(days);
        }
        return workDays;
    }

    private static int[] listToArray(final List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(final String[] args) {
        final int[] progresses = {95, 90, 99, 99, 80, 99};
        final int[] speeds = {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(solution(progresses, speeds)));
    }
}
