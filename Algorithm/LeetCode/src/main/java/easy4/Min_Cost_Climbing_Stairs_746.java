package easy4;

// TODO:
public class Min_Cost_Climbing_Stairs_746 {
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = cost[0];

        // i번째의 최소 비용 => min(i-1번째의 최소 비용 + i번째 계단 밟기, i-2번째의 최소 비용 + i번째 계단 밟기)
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = cost[i - 1] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[cost.length], dp[cost.length - 1]);
    }

    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        minCostClimbingStairs(cost);
    }
}
