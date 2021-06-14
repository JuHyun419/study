package kit.binarysearch;

import java.util.Arrays;

public class 입국심사2 {
    public static long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 1;
        long right = (long) n * times[times.length - 1];

        while (right >= left) {
            long mid = (left + right) / 2;
            long sum = getAllTimes(times, mid);

            if (sum >= n) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private static long getAllTimes(int[] times, long mid) {
        long sum = 0;
        for (int time : times) {
            sum += mid / time;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        System.out.println(solution(n, times));
    }
}
