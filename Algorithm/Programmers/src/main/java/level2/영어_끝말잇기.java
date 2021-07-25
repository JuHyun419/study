package level2;

import java.util.ArrayList;
import java.util.List;

public class 영어_끝말잇기 {
    public int[] solution(int n, String[] words) {
        List<String> existWords = new ArrayList<>();
        existWords.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            // 이미 말한 단어 OR 끝말잇기가 아닌 경우
            if (existWords.contains(words[i]) || isNotSameWords(words[i - 1], words[i])) {
                final int peopleNumber = i % n + 1;
                final int failNumber = i / n + 1;
                return new int[]{peopleNumber, failNumber};
            }
            existWords.add(words[i]);
        }
        return new int[]{0, 0};
    }

    private boolean isNotSameWords(final String beforeWord, final String currentWord) {
        return beforeWord.charAt(beforeWord.length() - 1) != currentWord.charAt(0);
    }
}
