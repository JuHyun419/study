package level2;

import java.util.Arrays;

public class 타겟_넘버 {
    private static int answer = 0;

    public static int solution(int[] numbers, int target) {
        dfs(numbers, target, 0);
        return answer;
    }

    public static void dfs(int[] numbers, int target, int count) {
        if (count == numbers.length) {
            int sum = Arrays.stream(numbers).sum();
            answer += (sum == target) ? 1 : 0;
            return;
        }

        dfs(numbers, target, count + 1);
        numbers[count] *= -1;
        dfs(numbers, target, count + 1);
    }

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(solution(numbers, target));
    }
}
