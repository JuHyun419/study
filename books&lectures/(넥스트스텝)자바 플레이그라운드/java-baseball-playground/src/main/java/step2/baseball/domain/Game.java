package step2.baseball.domain;

import step2.baseball.utils.Message;
import step2.baseball.utils.Valid;
import step2.baseball.view.InputView;
import step2.baseball.view.OutputView;

import java.util.List;

public class Game {
    private boolean checkReStart;

    public void playGame() {
        List<Integer> computerNumbers = NumberGenerator.of();
        checkReStart = false;

        while (!checkReStart) {
            String player = InputView.inputNumber();
            Valid.checkNumbersSize(player, player.length());

            int ball = Score.ballScore(computerNumbers, player);
            int strike = Score.strikeScore(computerNumbers, player);
            OutputView.printResult(ball, strike);
            checkIfSuccessGame(strike);
        }
    }

    public void checkIfSuccessGame(int strike) {
        if (isSuccess(strike)) {
            checkReStart = isReStartGame();
        }
    }

    public boolean isSuccess(int strikeCount) {
        return strikeCount == 3;
    }

    public boolean isReStartGame() {
        String menu = InputView.reGame();
        Valid.checkReStartInput(menu);

        return menu.equals(Message.END_INPUT);
    }

}
