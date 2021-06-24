package kakao.blind_2018;

import java.util.LinkedList;

public class 캐시 {
    public static int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return 5 * cities.length;
        }

        LinkedList<String> queue = new LinkedList<>();
        int answer = 0;

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();

            if (queue.contains(city)) { // cache hit
                queue.remove(city);
                queue.add(0, city);
                answer += 1;
            } else { // cache miss
                if (queue.size() == cacheSize) {
                    queue.pollLast();
                }

                queue.addFirst(city);
                answer += 5;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int cacheSize = 2;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA" };
    }

}
