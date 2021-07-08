package kakao.blind_2021;

public class 숫자_문자열과_영단어 {

    static final String[] WORDS =
            {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public int solution(String s) {
        StringBuilder sb = new StringBuilder();

        while (s.length() != 0) {
            if (isStringNumber(s)) { // 문자가 숫자인 경우 => 그대로 숫자를 넣고 그 뒤부터 판단
                sb.append(s.charAt(0));
                s = s.substring(1);
            } else { // 숫자가 아닌 경우 => 영단어를 찾아서 넣고, 해당 단어 제거
                int number = findNumber(s);
                String word = WORDS[number];
                sb.append(number);
                s = s.replaceFirst(word, "");
            }
        }
        return Integer.parseInt(sb.toString());
    }

    private boolean isStringNumber(String s) {
        return s.charAt(0) >= '0' && s.charAt(0) <= '9';
    }

    private int findNumber(String s) {
        int index = 0;
        for (int i = 0; i < WORDS.length; i++) {
            if (s.startsWith(WORDS[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    // wow
    public int solution2(String s) {
        for (int i = 0; i < WORDS.length; i++) {
            if (s.contains(WORDS[i])) {
                s = s.replaceAll(WORDS[i], Integer.toString(i));
            }
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        String str = "one4seven7";

        System.out.println(str.replaceFirst("seven", "7"));
    }
}
