package step2.baseball.domain;

import java.util.List;

import static step2.baseball.utils.Converter.arrayToList;
import static step2.baseball.utils.Converter.charToInt;
import static step2.baseball.utils.Converter.strToIntArray;

public class Score {

    /**
     * 컴퓨터의 수와 플레이어의 수 중 볼의 갯수를 카운트한다.
     *  - 볼(ball): 같은 수가 다른 자리에 있는 경우
     * @return ballCount
     */
    public static int ballScore(List<Integer> computerNumbers, String playerNumbers) {
        int ballCount = 0;
        List<Integer> playerNums = arrayToList(strToIntArray(playerNumbers));

        if (computerNumbers.get(0) == playerNums.get(1) || computerNumbers.get(0) == playerNums.get(2)) ballCount ++;
        if (computerNumbers.get(1) == playerNums.get(2) || computerNumbers.get(1) == playerNums.get(0)) ballCount ++;
        if (computerNumbers.get(2) == playerNums.get(0) || computerNumbers.get(2) == playerNums.get(1)) ballCount ++;
        return ballCount;
    }

    /**
     * 컴퓨터의 수와 플레이어의 수 중 스트라이크의 갯수를 카운트한다.
     *  - 스트라이크(strike): 같은 수가 같은 자리에 있는 경우
     * @return strikeCount
     */
    public static int strikeScore(List<Integer> computerNumbers, String playerNumbers) {
        int strikeCount = 0;
        for (int i = 0; i < playerNumbers.length(); i++) {
            int number = charToInt(playerNumbers.charAt(i));
            if (isStrike(computerNumbers.get(i), number)) {
                strikeCount ++;
            }
        }
        return strikeCount;
    }

    private static boolean isStrike(int computerNumber, int playerNumber) {
        return computerNumber == playerNumber;
    }
}
