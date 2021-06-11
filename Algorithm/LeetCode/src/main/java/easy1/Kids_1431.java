package easy1;

import java.util.ArrayList;
import java.util.List;

public class Kids_1431 {

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        int max = getMax(candies);
        int minimumCandies = max - extraCandies;
        for (int i = 0; i < candies.length; i++) {
            if (isPossibleDistribution(minimumCandies, candies[i]))
                result.add(true);
            else
                result.add(false);
        }
        return result;
    }

    private static int getMax(int[] candies) {
        int max = -1;

        for (int candy : candies) {
            if (candy > max) {
                max = candy;
            }
        }
        return max;
    }

    private static boolean isPossibleDistribution(int num1, int num2) {
        return num1 <= num2;
    }

    public static void main(String[] args) {
        int[] candies = {2, 3, 5, 1, 3};
        int extraCandies = 3;

        List<Boolean> result = kidsWithCandies(candies, extraCandies);

        for (Boolean aBoolean : result) {
            System.out.println(aBoolean);
        }
    }
}
