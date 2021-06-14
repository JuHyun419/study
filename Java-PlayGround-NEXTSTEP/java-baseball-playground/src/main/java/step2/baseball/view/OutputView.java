package step2.baseball.view;

import step2.baseball.utils.Message;

public class OutputView {

    private OutputView() { }

    public static void printStartInput() {
        System.out.print(Message.START_MESSAGE);
    }

    public static void printResult(int ballCount, int strikeCount) {
        if (ballCount == 0 && strikeCount == 0) {
            System.out.println(Message.NOTHING);
            return;
        }

        if (strikeCount == 3) {
            System.out.println(Message.SUCCESS + "\n" + Message.NOTICE);
            return;
        }

        if (ballCount != 0) {
            System.out.println(ballCount + Message.BALL);
        }

        if (strikeCount != 0) {
            System.out.println(strikeCount + Message.STRIKE);
        }
    }

}
