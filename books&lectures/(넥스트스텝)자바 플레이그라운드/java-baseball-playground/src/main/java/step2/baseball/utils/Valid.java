package step2.baseball.utils;

import java.util.List;

public class Valid {
    private Valid () { }

    public static void checkNumbersRange(List<Integer> numbers, int min, int max) {
        if (!numbers.stream().allMatch(i -> i >= min && i <= max)) {
            throw new IllegalArgumentException("숫자의 범위는 1에서 9사이여야 합니다.");
        }
    }

    public static void checkNumbersSize(List<Integer> numbers, int size) {
        if (numbers.size() != size) {
            throw new IllegalArgumentException("숫자는 3자리여야 합니다.");
        }
    }

    public static void checkNumbersSize(String player, int size) {
        if (player.length() != size) {
            throw new IllegalArgumentException("숫자는 3자리여야 합니다.");
        }
    }

    public static void checkReStartInput(String menu) {
        if (!menu.equals("1") && !menu.equals("2")) {
            throw new IllegalArgumentException("메뉴는 1 또는 2만 입력하셔야 합니다.");
        }
    }
}
