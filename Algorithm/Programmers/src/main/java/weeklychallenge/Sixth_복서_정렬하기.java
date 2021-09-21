package weeklychallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sixth_복서_정렬하기 {

    public int[] solution(int[] weights, String[] head2head) {
        List<Boxer> boxerList = new ArrayList<>();

        for (int i = 0; i < weights.length; i++) {
            int victoryCount = 0; // 자신보다 몸무게가 무거운 복서를 이긴 횟수
            int game = 0;   // 복서 게임 횟수
            int win = 0;    // 이긴 횟수
            for (int j = 0; j < head2head[i].length(); j++) {
                // 자기 자신 or 붙어본 적 없는경우 패스
                if (i == j || head2head[i].charAt(j) == 'N') {
                    continue;
                }

                // j+1 복서에게 이긴경우
                if (head2head[i].charAt(j) == 'W') {
                    win++;
                    // 자기 자신보다 몸무게가 무거울 경우
                    if (weights[i] < weights[j]) {
                        victoryCount++;
                    }
                }
                game++;
            }
            double victoryRate = (game == 0) ? 0 : (double) win / game * 100;
            boxerList.add(new Boxer(victoryRate, victoryCount, weights[i], (i + 1)));
        }
        Collections.sort(boxerList);
        int[] answer = new int[weights.length];

        for (int i = 0; i < boxerList.size(); i++) {
            answer[i] = boxerList.get(i).number;
        }
        return answer;
    }

    static class Boxer implements Comparable<Boxer> {
        double victoryRate;
        int victoryCount;
        int weight;
        int number;

        public Boxer(double victoryRate, int victoryCount, int weight, int number) {
            this.victoryRate = victoryRate;
            this.victoryCount = victoryCount;
            this.weight = weight;
            this.number = number;
        }

        /*
        정렬 기준
         1) 승률이 높은 순
         2) 승률이 동일한 경우 -> 자신보다 몸무게가 무거운 복서를 이긴 횟수가 많은 순
         3) 횟수가 동일한 경우 -> 몸무게가 무거운 순
         4) 몸무게도 동일한 경우 -> 작은 번호 순
         */
        @Override
        public int compareTo(Boxer o) {
            if (this.victoryRate == o.victoryRate) {
                if (this.victoryCount == o.victoryCount) {
                    if (this.weight == o.weight) {
                        return Integer.compare(this.number, o.number);
                    }
                    return Integer.compare(o.weight, this.weight);
                }
                return Integer.compare(o.victoryCount, this.victoryCount);
            }
            return Double.compare(o.victoryRate, this.victoryRate);
        }
    }
}
