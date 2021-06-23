package easy5;

public class Find_Pivot_Index_724 {
    public int pivotIndex(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int leftSum = 0;
            int rightSum = 0;

            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }

            for (int j = i + 1; j < nums.length; j++) {
                rightSum += nums[j];
            }

            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }


    // O(N)
    public int pivotIndex2(int[] nums) {
        int totalSum = 0, leftSum = 0;
        for (int i : nums) totalSum += i;
        for (int i = 0; i < nums.length; i++) {
            // index 0 has no left
            if (i != 0) leftSum += nums[i - 1];
            int rightSum = totalSum - leftSum - nums[i];
            if (rightSum == leftSum) return i;
        }
        return -1;
    }
}
