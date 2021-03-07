package easy1;

public class How_1365 {

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] output = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;

                if (nums[j] < nums[i]) {
                    count ++;
                }
            }
            output[i] = count;
        }
        return output;
    }

    public static void main(String[] args) {

    }
}
