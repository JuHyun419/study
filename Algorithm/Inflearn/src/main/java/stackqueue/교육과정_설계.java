package stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 교육과정_설계 {

    public static void main(String[] args) {
        교육과정_설계 T = new 교육과정_설계();
        Scanner kb = new Scanner(System.in);
        String a = kb.next();
        String b = kb.next();
        System.out.println(T.solution2(a, b));
    }

    /**
     * Queue
     *
     * @param classes: 필수과목 순서
     * @param plan:    수업설계
     * @return
     */
    private String solution(String classes, String plan) {
        Queue<Character> queue = new LinkedList<>();

        for (char ch : classes.toCharArray()) {
            queue.offer(ch);
        }

        int i = 0;

        while (!queue.isEmpty() && i < plan.length()) {
            if (queue.peek() == plan.charAt(i)) {
                queue.poll();
            }
            i++;
        }

        return queue.isEmpty() ? "YES" : "NO";
    }

    /* StringBuilder */
    private String solution2(String classes, String plan) {
        StringBuilder sb = new StringBuilder(classes);
        int i = 0;

        while (sb.length() != 0 && i < plan.length()) {
            if (plan.charAt(i) == sb.charAt(0)) {
                sb.deleteCharAt(0);
            }
            i++;
        }

        return sb.length() == 0 ? "YES" : "NO";
    }

}
