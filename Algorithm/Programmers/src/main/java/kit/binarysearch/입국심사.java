package kit.binarysearch;

import java.util.Arrays;

// TODO:
public class 입국심사 {
    /**
     * 문제 풀이
     * 1) 이분탐색에 필요한 left, right 구하기
     *  - left, right: 모든 사람이 입국심사를 받는데 걸리는 최소, 최대 시간
     * 2) 이분탐색으로 주어진 시간(mid)동안 심사관이 몇명을 검사할 수 있는지 계산하기
     * 3) 2)에서 계산 한 사람의 수와 n을 비교하기
     * 4) 비교값을 통해 탐색의 범위 좁혀가기
     *
     * @param n: 입국심사를 기다리는 사람 수
     * @param times: 각 심사관이 한 명을 심사하는데 걸리는 시간
     */
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
