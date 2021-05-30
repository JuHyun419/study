package easy2;

import java.util.Arrays;

public class Defuse_the_Bomb_1652 {

    public static int[] decrypt(final int[] code, final int k) {
        final int length = code.length;
        final int[] result = new int[length];

        if (k == 0) {
            Arrays.fill(result, 0);
            return result;
        }

        if (k > 0) { // next k
            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < (i + k + 1); j++) {
                    int codeIndex = j;
                    if (isGreaterThanSize(codeIndex, length))
                        codeIndex -= length;

                    result[i] += code[codeIndex];
                }
            }
        } else { // previous k
            for (int i = 0; i < length; i++) {
                for (int j = i - 1; j > i + k - 1; j--) {
                    int codeIndex = j;
                    if (codeIndex < 0)
                        codeIndex += length;

                    result[i] += code[codeIndex];
                }
            }
        }
        return result;
    }

    private static boolean isGreaterThanSize(final int var1, final int var2) {
        return var1 >= var2;
    }

    public static void main(final String[] args) {
        final int[] code = {5, 7, 1, 4};
        final int[] result = decrypt(code, 3);
        System.out.println(Arrays.toString(result));

        final int[] code2 = {2, 4, 9, 3};
        final int[] result2 = decrypt(code2, -2);
        System.out.println(Arrays.toString(result2));
    }
}
