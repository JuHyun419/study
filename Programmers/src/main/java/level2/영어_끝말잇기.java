package level2;

import java.util.ArrayList;
import java.util.List;

public class 영어_끝말잇기 {

    public static int[] solution(int n, String[] words) {
        int manNum;
        int failNum;
        List<String> wordList = new ArrayList<>();
        wordList.add(words[0]);

        /*
          규칙 유추하기.. 인덱스를 기준으로 적어가면서 파악
          n = 3,  i = 8 ==> {3, 3}
          n = 2,  i = 4 ==> {1, 3}
         */
        for (int i = 1; i < words.length; i++) {
            // 이전에 등장한 단어
            if (wordList.contains(words[i])) {
                manNum = (i % n) + 1;
                failNum = (i / n) + 1;
                return new int[]{manNum, failNum};
            }

            // 앞 단어의 끝 문자와 뒷 단어의 첫 문자가 다른 경우(끝말잇기 X)
            if (words[i-1].charAt(words[i-1].length() - 1) != words[i].charAt(0)) {
                manNum = (i % n) + 1;
                failNum = (i / n) + 1;
                return new int[]{manNum, failNum};
            }
            wordList.add(words[i]);
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) {

    }
}
