package implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class 할아버지는_유명해 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Map<Integer, Integer> people = new HashMap<>();
            int N = scanner.nextInt(); // 기간
            int M = scanner.nextInt(); // 랭킹 정보

            // 종료 조건
            if (N == 0 && M == 0) {
                scanner.close();
                return;
            }

            // 선수의 랭킹 포인트 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int ranking = scanner.nextInt();
                    people.put(ranking, people.getOrDefault(ranking, 0) + 1);
                }
            }

            int secondScore = getSecondScore(people);
            List<Integer> ranking = getSecondPeople(people, secondScore);

            printPeopleNumber(ranking);
        }
    }

    /* 2등 스코어  */
    private static int getSecondScore(Map<Integer, Integer> peoples) {
        return getSecondValue(mapValueToList(peoples));
    }

    private static List<Integer> mapValueToList(Map<Integer, Integer> peoples) {
        List<Integer> scores = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : peoples.entrySet()) {
            scores.add(entry.getValue());
        }
        return scores;
    }

    private static int getSecondValue(List<Integer> list) {
        list.sort(Collections.reverseOrder()); // 역순 정렬

        int secondValue = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (secondValue != list.get(i)) {
                return list.get(i);
            }
        }
        return secondValue;
    }

    private static List<Integer> getSecondPeople(Map<Integer, Integer> people, int secondScore) {
        List<Integer> list = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : people.entrySet()) {
            if (secondScore == entry.getValue()) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    private static void printPeopleNumber(List<Integer> ranking) {
        Collections.sort(ranking);

        for (Integer number : ranking) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

}
