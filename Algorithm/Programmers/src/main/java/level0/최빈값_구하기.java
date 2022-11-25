package level0;

import java.util.HashMap;
import java.util.Map;

public class 최빈값_구하기 {

    public int solution(int[] array) {
        Map<Integer, Integer> numbers = new HashMap<>();
        int max = 0;

        for (int number : array) {
            numbers.put(number, numbers.getOrDefault(number, 0) + 1);
            max = Math.max(max, numbers.get(number));
        }

        int answer = 0;
        int count = 0;

        for (Integer number : numbers.keySet()) {
            if (numbers.get(number) == max) {
                answer = number;
                count++;
            }
        }

        return (count > 1) ? -1 : answer;
    }

}
