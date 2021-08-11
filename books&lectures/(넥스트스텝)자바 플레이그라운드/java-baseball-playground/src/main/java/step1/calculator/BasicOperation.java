package step1.calculator;

import java.util.Arrays;

public enum BasicOperation implements Operation {

    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    MULTIPLY("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String operator;

    BasicOperation(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    private static BasicOperation of(String operator) {
        return Arrays.stream(BasicOperation.values())
                .filter(e -> e.getOperator().equals(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(operator + "는 올바른 연산자가 아닙니다."));
    }

    public static double calculate(String operator, double num1, double num2) {
        BasicOperation basicOperation = BasicOperation.of(operator);
        return basicOperation.apply(num1, num2);
    }

}
