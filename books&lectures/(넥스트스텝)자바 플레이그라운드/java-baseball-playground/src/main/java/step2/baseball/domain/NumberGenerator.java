package step2.baseball.domain;

import step2.baseball.utils.Valid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NumberGenerator {
    private static final Random random = new Random();
    private static final int NUMBERS_COUNT = 3;
    private static final int NUMBERS_BOUND = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    private NumberGenerator() {
    }

    public static List<Integer> of() {
        Set<Integer> set = new HashSet<>();

        while (set.size() < NUMBERS_COUNT) {
            final int randomNumber = random.nextInt(NUMBERS_BOUND) + 1;
            set.add(randomNumber);
        }

        List<Integer> list = toList(set);
        Valid.checkNumbersSize(list, list.size());
        Valid.checkNumbersRange(list, MIN_NUMBER, MAX_NUMBER);

        return list;
    }

    private static <T> List<T> toList(Set<T> set) {
        return new ArrayList<>(set);
    }
}
