import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// TODO:
public class 수들의_합_2_2003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N + 1];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(twoPointer(numbers, M));
    }

    private static int twoPointer(int[] numbers, int M) {
        int count = 0;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < numbers.length) {
            if (sum == M) {
                count++;
            }

            if (sum >= M) {
                sum -= numbers[left++];
            } else {
                sum += numbers[right++];
            }
        }
        return count;
    }
}
