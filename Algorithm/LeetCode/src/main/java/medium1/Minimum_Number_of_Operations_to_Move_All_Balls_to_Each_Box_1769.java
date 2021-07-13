package medium1;

import java.util.Arrays;

public class Minimum_Number_of_Operations_to_Move_All_Balls_to_Each_Box_1769 {
    public static int[] minOperations(String boxes) {
        int[] result = new int[boxes.length()];

        for (int i = 0; i < boxes.length(); i++) {
            int number = 0;
            for (int j = 0; j < boxes.length(); j++) {
                if (boxes.charAt(j) == '1') {
                    number += Math.abs(j - i);
                }
            }
            result[i] = number;
        }
        return result;
    }

    public static void main(String[] args) {
        String boxes = "001011";
        System.out.println(Arrays.toString(minOperations(boxes)));
    }
}
