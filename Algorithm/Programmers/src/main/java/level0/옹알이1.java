package level0;

public class 옹알이1 {

    public static int solution(String[] babbling) {
        int answer = 0;
        String[] baby = {"aya", "ye", "woo", "ma"};

        for (String word : babbling) {
            // 아기가 사용할 수 있는 발음 조합 모두 !로 치환
            // 공백으로 치환할 경우 "wyeoo" 처럼 반례 케이스가 존재(ye 치환 후 woo 치환)
            for (String babyWord : baby) {
                word = word.replace(babyWord, "!");
            }

            // 발음할 수 있는 조합인지 체크
            boolean canSpeak = true;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != '!') {
                    canSpeak = false;
                    break;
                }
            }

            if (canSpeak) {
                answer++;
            }
        }

        return answer;
    }


    public static void main(String[] args) {
        String[] babbling = {"aya", "yee", "u", "maa", "wyeoo"};
        System.out.println(solution(babbling));
    }

}
