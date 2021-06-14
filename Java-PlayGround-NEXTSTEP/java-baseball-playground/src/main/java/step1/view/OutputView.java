package step1.view;

public class OutputView {
    private static final String RESULT_MESSAGE = "문자열 계산 결과: ";

    private OutputView() {
    }

    public static void printResult(double result) {
        System.out.println(RESULT_MESSAGE + result);
    }
}
