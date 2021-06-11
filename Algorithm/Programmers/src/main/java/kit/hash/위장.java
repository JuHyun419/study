package kit.hash;

import java.util.HashMap;
import java.util.Map;

public class 위장 {

    public static int solution(final String[][] clothes) {
        int answer = 1;
        final Map<String, Integer> map = new HashMap<>();

        for (final String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }

        for (final String key : map.keySet()) {
            answer *= (map.get(key) + 1);
        }

        return answer - 1;
    }

    public static void main(final String[] args) {
        final String[][] clothes = {{"yellow", "h"}, {"blue", "e"}, {"green", "h"}};
        System.out.println(solution(clothes));
    }

}
