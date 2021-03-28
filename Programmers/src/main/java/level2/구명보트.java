package level2;

import java.util.Arrays;

public class 구명보트 {

    public int solution(int[] people, int limit) {
        int answer = 0;
        int left = 0;
        Arrays.sort(people);

        for (int i = people.length - 1; i >= 0; i--) {
            if (left > i) break;

            if (people[i] + people[left] <= limit) {
                left += 1;
            }
            answer ++;
        }
        return answer;
    }

}
