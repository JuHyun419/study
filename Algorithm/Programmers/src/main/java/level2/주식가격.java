package level2;

public class 주식가격 {

    public static int[] solution(int[] prices) {
        int length = prices.length;
        int[] answer = new int[length];

        for (int i = 0; i < length; i++) {
            int second = 1;

            for (int j = i + 1; j < length; j++) {
                if (prices[i] > prices[j] || j == length - 1) {
                    answer[i] = second;
                    break;
                }
                second ++;
            }
        }
        answer[length - 1] = 0;
        return answer;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        int[] answer = solution(prices);

        for (int i : answer) {
            System.out.println(i);
        }
    }
}
