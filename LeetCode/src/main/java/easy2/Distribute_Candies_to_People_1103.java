package easy2;

import java.util.Arrays;

// TODO:
public class Distribute_Candies_to_People_1103 {
    public static int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int distributeCount = 1;

        while (candies > 0) {
            for (int i = 0; i < num_people; i++) {
                if (distributeCount > candies) {
                    result[i] += candies;
                    candies = 0;
                    break;
                } else {
                    candies -= distributeCount;
                    result[i] += distributeCount++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int candies = 7;
        int num = 4;

        int[] arr = distributeCandies(candies, num);
        System.out.println(Arrays.toString(arr));
    }
}
