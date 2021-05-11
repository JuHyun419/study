package easy3;

import java.util.Arrays;

public class Fair_Candy_Swap_888 {

    public static int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int[] result = new int[2];
        int aliceSum = Arrays.stream(aliceSizes).sum();
        int bobSum = Arrays.stream(bobSizes).sum();

        for (int aliceSize : aliceSizes) {
            for (int bobSize : bobSizes) {
                final int afterAliceSum = aliceSum - aliceSize + bobSize;
                final int afterBobSum = bobSum - bobSize + aliceSize;

                if (afterAliceSum == afterBobSum) {
                    result[0] = aliceSize;
                    result[1] = bobSize;
                    break;
                }
            }
            if (findSameCandy(result[0])) {
                break;
            }
        }
        return result;
    }

    private static boolean findSameCandy(final int num) {
        return num != 0;
    }

    public static void main(String[] args) {
        final int[] aliceSizes = {1, 1};
        final int[] bobSizes = {2, 2};
        final int[] result = fairCandySwap(aliceSizes, bobSizes);

        System.out.println(Arrays.toString(result));
    }

}
