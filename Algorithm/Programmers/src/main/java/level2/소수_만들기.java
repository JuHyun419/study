package level2;

public class 소수_만들기 {

    public static int solution(int[] nums) {
        int answer = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    answer += isPrime(sum) ? 1 : 0;
                }
            }
        }
        return answer;
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] nums2 = {1, 2, 7, 6, 4};

        System.out.println(solution(nums));
        System.out.println(solution(nums2));
    }
}
