package weeklychallenge;

public class 부족한_금액_계산하기_1주차 {
    public long solution(int price, int money, int count) {
        long sum = 0;
        for (int i = 1; i <= count; i++) {
            sum += (long) price * i;
        }
        return (money >= sum) ? 0 : sum - money;
    }
}
