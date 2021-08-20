package kakao.blind_2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// TODO: 투포인터
public class 보석_쇼핑 {
    public int[] solution(String[] gems) {
        /**
         * Set: 모든 종류의 보석 갯수
         * Map: 각 보석당 갯수
         * Queue: 구매한 보석을 담는 큐
         * start: 시작 진열대 번호
         * end: 끝 진열대 번호
         * index: 갱신할 시작 진열대 번호
         * len: 모든 종류의 보석을 구매한 구간
         */
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        int len = gems.length + 1;
        int start = 0, end = 0, index = 0;

        for (String gem : gems) {
            queue.add(gem);
            map.put(gem, map.getOrDefault(gem, 0) + 1);

            // 현재 구매한 보석이 이전에 구매했던 보석인경우
            // ex) Queue: [DIA - RUBY - RUBY] 에서 DIA를 구매한 경우
            while (map.get(queue.peek()) > 1) {
                // 큐에서 보석을 빼고, Map에서 해당 보석의 갯수 -1
                map.put(queue.peek(), map.get(queue.poll()) - 1);
                // 시작 진열대 번호 + 1(가장 짧은 구간을 찾아야 하므로)
                index++;
            }

            // 모든 종류의 보석을 구매했을 경우 && 이전에 구했던 구간보다 짧은 구간인 경우
            // 시작 진열대
            if (set.size() == map.size() && len > queue.size()) {
                len = queue.size();
                start = index + 1;
                end = start + len - 1;
            }
        }
        return new int[]{start, end};
    }
}
