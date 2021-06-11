package easy2;

public class Distribute_Candies_to_People_1103 {
    public static int[] distributeCandies(int candies, final int num_people) {
        final int[] result = new int[num_people];
        int given = 1;
        while (candies > 0) {
            for (int i = 0; i < num_people; i++) {
                if (isNotDistributeCandy(given, candies)) {
                    result[i] += candies;
                    candies = 0;
                    break;
                }
                result[i] += given;
                candies -= given;
                given++;
            }
        }
        return result;
    }

    private static boolean isNotDistributeCandy(final int given, final int candy) {
        return given > candy;
    }
}
