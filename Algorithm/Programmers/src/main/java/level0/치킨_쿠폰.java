package level0;

public class 치킨_쿠폰 {

    public static int solution(int chicken) {
        int answer = 0;

        while (chicken >= 10) {
            int newChicken = chicken / 10;
            int restChicken = chicken % 10;
            chicken = newChicken + restChicken;

            answer += newChicken;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(1081));
    }

}
