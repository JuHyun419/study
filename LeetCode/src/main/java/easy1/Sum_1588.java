package easy1;

public class Sum_1588 {

    public static int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;

        for (int i = 1; i <= n; i += 2) {
            int startIndex = 0;
            int endIndex = i;

            while (endIndex <= n) {
                for (int j = startIndex; j < endIndex; j++) {
                    sum += arr[j];
                }
                startIndex ++;
                endIndex ++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 5, 3};
        System.out.println(sumOddLengthSubarrays(arr));
    }
}
