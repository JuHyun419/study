package refactoring;

import java.util.Arrays;

public class After {
    public static int splitAndSum(String text) {
        if (isBlank(text)) {
            return 0;
        }
        return sum(toInts(split(text))); // Decorator
    }

    private static boolean isBlank(final String text) {
        return (text == null || text.isEmpty());
    }

    private static String[] split(final String text) {
        return text.split("[,:]");
    }

    private static int[] toInts(final String[] values) {
        return Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
    }

    private static int sum(final int[] value) {
        return Arrays.stream(value).sum();
    }
}
