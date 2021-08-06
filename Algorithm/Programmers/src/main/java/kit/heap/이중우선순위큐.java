package kit.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class 이중우선순위큐 {
    public static int[] solution(String[] operations) {
        Queue<Integer> minQueue = new PriorityQueue<>();
        Queue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i].split(" ")[0];
            int number = Integer.parseInt(operations[i].split(" ")[1]);

            if (operation.equals("I")) {
                minQueue.add(number);
                maxQueue.add(number);
            } else if (operation.equals("D")) {
                if (minQueue.isEmpty() || maxQueue.isEmpty()) continue;
                if (number == 1) { // 최댓값 삭제
                    minQueue.remove(maxQueue.poll());
                } else if (number == -1) { // 최솟값 삭제
                    maxQueue.remove(minQueue.poll());
                }
            }
        }

        return minQueue.isEmpty()
                ? new int[]{0, 0}
                : new int[]{maxQueue.peek(), minQueue.peek()};
    }

    public static void main(String[] args) {
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(solution(operations)));

    }
}
