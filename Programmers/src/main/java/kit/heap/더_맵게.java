package kit.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class 더_맵게 {
    public static int solution(final int[] scoville, final int K) {
        int answer = 0;
        final Queue<Integer> queue = new PriorityQueue<>();
        for (final int j : scoville) queue.add(j);

        while (queue.size() > 1 && queue.peek() < K) {
            final int num1 = queue.poll();
            final int num2 = queue.poll();
            final int newNum = num1 + (num2 * 2);
            queue.add(newNum);
            answer ++;
        }
        return queue.peek() < K ? -1 : answer;
    }

    public static void main(final String[] args) {
        final int[] scoville = {1, 2, 3, 9, 10, 12};
        final int k = 7;
        System.out.println(solution(scoville, k));
    }
}
