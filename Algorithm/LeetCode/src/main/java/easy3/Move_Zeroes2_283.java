package easy3;

public class Move_Zeroes2_283 {
    public void moveZeroes(int[] nums) {
        int[] temp = new int[nums.length];
        int index = 0;

        for (int i = 0; i < temp.length; i++) {
            if (nums[i] != 0) {
                temp[index++] = nums[i];
            }
        }
        System.arraycopy(temp, 0, nums, 0, nums.length);
    }
}
