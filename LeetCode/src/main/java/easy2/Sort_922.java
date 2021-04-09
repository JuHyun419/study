package easy2;

public class Sort_922 {

    public int[] sortArrayByParityII(int[] nums) {
        int oddIndex = 1;
        int evenIndex = 0;
        int[] sortNums = new int[nums.length];

        for (int num : nums) {
            if (isOdd(num)) {
                sortNums[oddIndex] = num;
                oddIndex += 2;
            } else {
                sortNums[evenIndex] = num;
                evenIndex += 2;
            }
        }
        return sortNums;
    }

    private boolean isOdd(final int num) {
        return num % 2 == 1;
    }

}
