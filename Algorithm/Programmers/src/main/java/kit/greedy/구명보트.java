package kit.greedy;

import java.util.Arrays;

public class 구명보트 {

    public static int solution(final int[] people, final int limit) {
        Arrays.sort(people);
        int answer = 0;
        int index = 0;

        for (int i = people.length - 1; i >= 0 && i >= index; i--) {
            if (people[i] + people[index] <= limit) {
                index ++;
            }
            answer ++;
        }
        return answer;
    }

    public static void main(final String[] args) {
        final int[] people = {70, 50, 80, 50};
        final int limit = 100;

        System.out.println(solution(people, limit));
    }

}
