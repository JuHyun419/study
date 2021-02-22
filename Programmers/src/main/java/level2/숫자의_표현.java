package level2;

public class 숫자의_표현 {

    public static int solution(int n) {
        int answer = 1;

        for (int i = 1; i <= n / 2; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += j;

                if (sum == n) {
                    answer++;
                }

                if (sum > n) {
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(15));
    }
}
