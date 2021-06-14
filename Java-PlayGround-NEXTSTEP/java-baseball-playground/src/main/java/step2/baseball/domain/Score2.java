package step2.baseball.domain;

import java.util.List;

public class Score2 {
    private int ball;
    private int strike;

    public void count(List<Integer> computers, List<Integer> players) {
        ball = 0;
        strike = 0;

        for (int i = 0; i < computers.size(); i++) {
            if (isStrike(computers.get(i), players.get(i))) {
                strike ++;
                continue;
            }
            if (computers.contains(players.get(i))) {
                ball ++;
            }
        }
    }

    private boolean isStrike(int computer, int player) {
        return computer == player;
    }

    public int getBall() {
        return ball;
    }

    public int getStrike() {
        return strike;
    }
}
