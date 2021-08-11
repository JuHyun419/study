package step2.baseball.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() { }

    public static String inputNumber() {
        OutputView.printStartInput();
        return scanner.next();
    }

    public static String reGame() {
        return scanner.next();
    }
}
