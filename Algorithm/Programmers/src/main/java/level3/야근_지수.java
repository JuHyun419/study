package level3;

import java.util.Arrays;

public class 야근_지수 {
    public long solution(int n, int[] works) {
        if (n >= getSum(works)) return 0;
        int length = works.length;

        while (n > 0) {
            int max = findMax(works);

            for (int i = 0; i < length; i++) {
                if (works[i] == max) {
                    works[i]--;
                    n --;
                }

                if (n == 0) break;
            }
        }
        return getOvertime(works);
    }

    private long getSum(final int[] works) {
        long sum = 0;
        for (int work : works) {
            sum += work;
        }
        return sum;
    }

    private int findMax(int[] works) {
        return Arrays.stream(works).max().getAsInt();
    }

    private long getOvertime(final int[] works) {
        long sum = 0;
        for (int work : works) {
            sum += ((long) work * work);
        }
        return sum;
    }
}
