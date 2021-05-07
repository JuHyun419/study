package easy2;

import java.util.Arrays;

public class Minimum_Distance_to_the_Target_Element_1848 {

    public int getMinDistance(int[] nums, int target, int start) {
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result = Math.min(result, Math.abs(i - start));
            }
        }
        return result;
    }
}
