import java.util.Scanner;

public class 부분수열의_합_1182 {

    private static int N;
    private static int S;
    private static int[] numbers;
    private static int answer = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        S = scanner.nextInt();
        numbers = new int[N];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
        }
        dfs(0, 0);
        if (S == 0) {
            answer--;
        }
        System.out.println(answer);
        scanner.close();
    }

    private static void dfs(int index, int sum) {
        if (index == N) {
            if (sum == S) {
                answer++;
            }
            return;
        }
        dfs(index + 1, sum + numbers[index]);
        dfs(index + 1, sum);
    }
}
