package easy4;

public class Find_the_Town_Judge_997 {
    public int findJudge(int n, int[][] trust) {
        if (n == 1) return 1;
        int[] rightOccurrence = new int[n + 1];
        int[] leftOccurrence = new int[n + 1];

        for (int[] arr : trust) {
            leftOccurrence[arr[0]] += 1;
            rightOccurrence[arr[1]] += 1;
        }
        for (int num = 1; num <= n; num++) {
            if (rightOccurrence[num] == n - 1 && leftOccurrence[num] == 0)
                return num;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 1;
        int[][] trust = {};

    }
}
