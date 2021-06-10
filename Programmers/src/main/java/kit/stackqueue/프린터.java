package kit.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

// TODO:
public class 프린터 {

    // 프린터에서 인쇄 요청이 들어온 순서와 중요도를 한 쌍으로 관리
    static class Printer {
        int location;
        int priority;

        public Printer(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Printer> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Printer(i, priorities[i]));
        }

        while (!queue.isEmpty()) {
            // Queue에서 현재 값보다 큰 값이 존재하는지 찾기
            boolean checkMax = existMaxNumber(queue, queue.peek().priority);

            // Queue에 현재 값보다 큰 값이 존재하는 경우 => 가장 앞에 있는 문서 꺼낸 후 가장 마지막에 넣기
            if (checkMax) {
                queue.offer(queue.poll());
                continue;
            }

            // 현재의 인쇄 목록의 위치가 요청한 위치와 동일한 경우
            if (queue.poll().location == location) {
                answer = priorities.length - queue.size();
                break;
            }
        }
        return answer;
    }

    private static boolean existMaxNumber(Queue<Printer> queue, int compare) {
        boolean result = false;
        for (Printer printer : queue) {
            if (printer.priority > compare) {
                result = true;
                break;
            }
        }
        return result;
    }
}
