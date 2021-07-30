package kakao.blind_2018;

import java.util.LinkedList;

/**
 * 캐시 교체 알고리즘: LRU(Least Recently Used)
 * => 가장 오랫동안 사용되지 않은 캐시의 메모리를 제거
 *  - 새로운 데이터가 들어온 경우
 *    1) 캐시가 가득 차있으면 가장 오래된 데이터를 제거하고 넣어준다.
 *    2) 캐시가 가득 차있지 않으면 캐시에 넣어준다.
 *  - 존재하는 데이터가 들어온 경우
 *    1) 해당 데이터를 꺼낸다.
 *    2) 가장 최근 데이터로 꺼낸 데이터를 다시 넣는다.
 */
public class 캐시 {

    private static final int CACHE_HIT  = 1;
    private static final int CACHE_MISS = 5;

    public static int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return CACHE_MISS * cities.length;
        }
        LinkedList<String> queue = new LinkedList<>();
        int answer = 0;

        for (String s : cities) {
            String city = s.toLowerCase();
            if (queue.contains(city)) { // cache hit
                queue.remove(city);
                queue.add(0, city);
                answer += CACHE_HIT;
            } else { // cache miss
                if (isFullCaching(cacheSize, queue.size())) {
                    queue.pollLast();
                }
                queue.addFirst(city);
                answer += CACHE_MISS;
            }
        }
        return answer;
    }

    private static boolean isFullCaching(int cacheSize, int size) {
        return size == cacheSize;
    }

    public static void main(String[] args) {
        int cacheSize = 2;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA" };
        System.out.println(solution(cacheSize, cities));
    }

}
