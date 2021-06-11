package kit.stackqueue;

public class 주식가격 {

    public int[] solution(final int[] prices) {
        final int length = prices.length;
        final int[] answer = new int[length];

        for (int i = 0; i < length - 1; i++) {
            int periodTime = 1;
            for (int j = i + 1; j < length; j++) {
                if (prices[i] > prices[j] || j == length - 1) {
                    answer[i] = periodTime;
                    break;
                }

                periodTime ++;
            }
        }

        answer[length - 1] = 0;
        return answer;
    }

}
