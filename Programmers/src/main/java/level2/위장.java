package level2;

import java.util.HashMap;
import java.util.Map;

public class 위장 {

    public static int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();

        for (String[] clothe : clothes) {
            String str = clothe[1];
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        for (String key : map.keySet()) {
            answer *= (map.get(key) + 1);
        }
        return answer - 1;
    }

}
