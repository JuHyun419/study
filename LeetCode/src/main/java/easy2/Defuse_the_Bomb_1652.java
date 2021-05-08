package easy2;

import java.util.Arrays;

// TODO:
public class Defuse_the_Bomb_1652 {

    public static int[] decrypt(int[] code, int k) {
        final int length = code.length;
        int[] result = new int[length];

        if (k == 0) { // only zero
            for (int i = 0; i < length; i++) {
                result[i] = 0;
            }
            return result;
        }

        if (k > 0) { // next k
            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < (i + k + 1); j++) {
                    int codeIndex = j;

                    if (isGreaterThanSize(codeIndex, length)) {
                        codeIndex -= length;
                    }

                    result[i] += code[codeIndex];
                }
            }
        } else { // previous k
            for (int i = 0; i < length; i++) {
                for (int j = i - 1; j > i + k - 1; j--) {
                    int codeIndex = j;

                    if (codeIndex < 0) {
                        codeIndex += length;
                    }

                    result[i] += code[codeIndex];
                }
            }
        }
        return result;
    }

    private static boolean isGreaterThanSize(int var1, int var2) {
        return var1 >= var2;
    }

    public static void main(String[] args) {
        final int[] code = {5, 7, 1, 4};
        final int[] result = decrypt(code, 3);
        System.out.println(Arrays.toString(result));

        final int[] code2 = {2, 4, 9, 3};
        final int[] result2 = decrypt(code2, -2);
        System.out.println(Arrays.toString(result2));
    }
}
