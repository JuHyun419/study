package stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 공주구하기 {

    public static void main(String[] args) {
        공주구하기 T = new 공주구하기();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int k = kb.nextInt();
        System.out.println(T.solution(n, k));
    }

    private int solution(int n, int k) {
        Queue<Integer> queue = getPositions(n);

        while (queue.size() != 1) {
            // k-1 숫자를 외친 왕자들은 뒷 자리로 이동
            for (int i = 0; i < k - 1; i++) {
                queue.add(queue.poll());
            }
            // k(특정숫자)를 외친 왕자는 제외
            queue.poll();
        }
        return queue.poll();
    }

    private Queue<Integer> getPositions(int n) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }
        return queue;
    }

}
