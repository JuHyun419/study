package step1.util;

public class ExceptionHandler extends RuntimeException {

    private ExceptionHandler() {
    }

    public static void ifNotOperation(String[] values) {
        if (values.length < Const.MINIMUM_VALUE_SIZE) {
            throw new IllegalArgumentException("연산을 하기 위해서 문자는 최소 3개 이상이 필요합니다.");
        }
    }
}
