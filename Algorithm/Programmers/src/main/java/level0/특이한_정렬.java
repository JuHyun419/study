package level0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 특이한_정렬 {

    public static int[] solution(int[] numberArray, int n) {
        int length = numberArray.length;
        List<Numbers> list = new ArrayList<>(length);

        // 정수 & 거리 차이 값
        Arrays.stream(numberArray)
                .forEach(number -> list.add(new Numbers(number, Math.abs(n - number))));

        Collections.sort(list);

        return list.stream()
                .map(Numbers::getNumber)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static class Numbers implements Comparable<Numbers> {
        private final int number;
        private final int distance;

        public Numbers(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }

        public int getNumber() {
            return number;
        }

        // 거리가 가까운 순으로 정렬 -> 거리가 가까우면 더 큰 수를 앞에 오도록 정렬
        @Override
        public int compareTo(Numbers o) {
            if (this.distance == o.distance) {
                return o.number - this.number;
            }
            return this.distance - o.distance;
        }

    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6};

        System.out.println(Arrays.toString(solution(numbers, 4)));
    }

}
