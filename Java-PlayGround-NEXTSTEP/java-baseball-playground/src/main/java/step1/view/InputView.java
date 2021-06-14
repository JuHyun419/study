package step1.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String userInput() {
        return scanner.nextLine();
    }

    public static void close() {
        scanner.close();
    }
}
