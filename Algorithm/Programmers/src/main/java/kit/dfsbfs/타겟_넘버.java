package kit.dfsbfs;

import java.util.Arrays;

public class 타겟_넘버 {
    static int answer = 0;
    public static int solution(int[] numbers, int target) {
        dfs(numbers, target, 0);
        return answer;
    }

    public static void dfs(int[] numbers, int target, int index) {
        if (index == numbers.length) {
            int sum = Arrays.stream(numbers).sum();
            if (sum == target) {
                answer ++;
            }
            return;
        }

        dfs(numbers, target, index + 1);
        numbers[index] *= -1;
        dfs(numbers, target, index + 1);
    }

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        int num = solution(numbers, target);
        System.out.println(num);
    }

}
