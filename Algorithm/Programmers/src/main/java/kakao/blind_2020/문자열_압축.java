package kakao.blind_2020;

// TODO:
public class 문자열_압축 {
    public static int solution2(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length() / 2; ++i) {
            int pos = 0;
            int len = s.length();

            for (; pos + i <= s.length(); ) {
                String unit = s.substring(pos, pos + i);
                pos += i;
                int cnt = 0;

                for (; pos + i <= s.length(); ) {
                    if (unit.equals(s.substring(pos, pos + i))) {
                        cnt++;
                        pos += i;
                    } else {
                        break;
                    }
                }

                if (cnt > 0) { // 연속된 문자가 존재하는 경우
                    len -= i * cnt; // aaa => 1 * 2 (3a)

                    // 연속된 문자의 숫자에 따른 길이
                    if (cnt < 9) {
                        len += 1;
                    } else if (cnt < 99) {
                        len += 2;
                    } else if (cnt < 999) {
                        len += 3;
                    } else {
                        len += 4;
                    }
                }
            }

            answer = Math.min(answer, len);
        }
        return answer;
    }


    public static int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            StringBuilder compress = new StringBuilder(); // 압축 문자열
            int count = 1;  // 문자의 개수

            for (int j = 0; j < s.length(); j += i) {
                int subIndex = j + i;
                String word = s.substring(j, subIndex);
                if (subIndex > s.length()) { // substring 할 범위를 벗어난 경우
                    break;
                }
                String compare = s.substring(subIndex, j + i + i);

                if (word.equals(compare)) {
                    count++;
                } else {
                    if (count == 1) {
                        compress.append(word);
                    } else {
                        compress.append(count + word);
                        count = 1;
                    }
                }
            }
            System.out.println(compress);
            answer = Math.min(answer, compress.length());
        }

        return answer;
    }

    public static void main(String[] args) {
        String str = "aabbaccc";
        System.out.println(solution(str));
    }

}
