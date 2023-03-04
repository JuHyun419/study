package level2;

import java.util.Arrays;
import java.util.Stack;

public class 뒤에_있는_큰_수_찾기 {

    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numbers.length; i++) {
            if (stack.isEmpty() || numbers[i - 1] > numbers[i]) {
                stack.push(i);
                continue;
            }

            // 현재 값(numbers[i]) 보다 작은 수 모두 찾기(뒷 큰수) -> [9, 7, 3, 5, 6] 케이스
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        return answer;
    }

    public static void main(String[] args) {
        final int[] numbers = {9, 1, 5, 3, 6, 2};

        System.out.println(Arrays.toString(solution(numbers)));
    }

}
