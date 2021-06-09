package kit.binarysearch;

import java.util.Arrays;

// TODO:
public class 입국심사 {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long left = 1;
        long right = (long) n * times[times.length - 1];

        while (left <= right) {
            // mid: 이분 탐색 -> 심사를 받는데 걸리는 시간
            // sum: 주어진 시간(mid)동안 심사를 받을 수 있는 사람 수
            // n: 비교대상(target)
            long mid = (left + right) / 2;
            long sum = 0;

            // 주어진 시간(mid)동안 심사관이 몇명을 검사할 수 있는지 계산
            for (int time : times) {
                sum += mid / time;
            }

            // 심사관이 모든 사람을 검사할 수 있는경우 -> 최소 시간 다시 탐색
            if (sum >= n) {
                right = mid - 1;
                answer = mid;
            } else { // 심사관이 모든 사람을 검사할 수 없는경우 -> 시간을 더 늘려서 다시 탐색
                left = mid + 1;
            }
        }
        return answer;
    }
}
