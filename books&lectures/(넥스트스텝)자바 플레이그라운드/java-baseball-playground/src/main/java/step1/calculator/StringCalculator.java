package step1.calculator;

import step1.util.Converter;
import step1.util.ExceptionHandler;
import step1.view.InputView;
import step1.view.OutputView;

/**
 * 문자열 계산기(단위 테스트 실습)
 * 요구사항
 * - 사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 한다.
 * - 문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다.
 * - 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
 * - 예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
 */
public class StringCalculator {
    private static final String DELIMITER = " ";

    public void run() {
        final double result = calcResult(InputView.userInput());
        OutputView.printResult(result);
        InputView.close();
    }

    public double calcResult(String value) {
        final String[] values = split(value);
        ExceptionHandler.ifNotOperation(values);

        String operator = values[1];
        double num1 = Converter.toDouble((values[0]));
        double num2 = Converter.toDouble((values[2]));
        double result = BasicOperation.calculate(operator, num1, num2);

        for (int i = 3; i < values.length - 1; i += 2) {
            operator = values[i];
            num1 = Converter.toDouble(values[i + 1]);
            result = BasicOperation.calculate(operator, result, num1);
        }
        return result;
    }

    private String[] split(String value) {
        return value.split(StringCalculator.DELIMITER);
    }
}
