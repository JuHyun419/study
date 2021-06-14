package kit.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class 프린터2 {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        Queue<Printer> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Printer(priorities[i], i));
        }

        while (!queue.isEmpty()) {
            boolean checkMaxNumber = isMaxNumber(queue);
            if (checkMaxNumber) {
                if (queue.peek().location == location) { // 현재 문서가 찾고하자는 문서일때
                    return answer;
                } else {
                    answer ++;
                    queue.poll();
                }
            } else { // 나머지 인쇄 대기목록에서 현재 문서보다 중요도가 높은 문서가 존재하는 경우
                queue.add(queue.poll());
            }
        }

        return answer;
    }

    private boolean isMaxNumber(Queue<Printer> queue) {
        int priority = queue.peek().priority;
        for (Printer printer : queue) {
            if (priority < printer.priority) {
                return false;
            }
        }
        return true;
    }

    static class Printer {
        int priority;
        int location;

        public Printer(int priority, int location) {
            this.priority = priority;
            this.location = location;
        }
    }

}
