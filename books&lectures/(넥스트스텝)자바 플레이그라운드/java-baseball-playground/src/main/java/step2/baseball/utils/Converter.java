package step2.baseball.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static int[] strToIntArray(String str) {
        int[] array = new int[str.length()];
        for (int i = 0; i < array.length; i++) {
            int number = charToInt(str.charAt(i));
            array[i] = number;
        }
        return array;
    }

    public static List<Integer> arrayToList(int[] array) {
        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }

    public static int charToInt(char ch) {
        return Integer.parseInt(String.valueOf(ch));
    }

}
