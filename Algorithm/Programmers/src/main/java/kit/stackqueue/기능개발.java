package kit.stackqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 기능개발 {

    private static final int PROCESS = 100;

    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> workDays = getWorkDays(progresses, speeds);
        List<Integer> features = new ArrayList<>();

        while (!workDays.isEmpty()) {
            int deploy = 1;
            final int top = workDays.poll();
            while (!workDays.isEmpty() && top > workDays.peek()) {
                deploy++;
                workDays.poll();
            }
            features.add(deploy);
        }
        return features.stream().mapToInt(Integer::intValue).toArray();
    }

    private Queue<Integer> getWorkDays(final int[] progresses, final int[] speeds) {
        Queue<Integer> workDays = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            final int days = (int) Math.ceil((double)(PROCESS - progresses[i]) / speeds[i]);
            workDays.add(days);
        }
        return workDays;
    }

    public static void main(String[] args) {
        final int[] progresses = {99, 99, 99};
        final int[] speeds = {1, 30, 5};
        기능개발 a = new 기능개발();
        System.out.println(Arrays.toString(a.solution(progresses, speeds)));
    }
}
