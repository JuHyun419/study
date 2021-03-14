package level2;

import java.util.Arrays;

// TODO: 20210314
public class 구명보트 {

    public static int solution(int[] people, int limit) {
        int answer = 0;
        int i = 0;
        Arrays.sort(people);

        for (int j = people.length - 1; i <= j; j--) {
            if (people[j] + people[i] <= limit) {
                i++;
            }
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {

    }
}
