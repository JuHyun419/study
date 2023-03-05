package level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 모음사전 {

    private static final char[] words = {'A', 'E', 'I', 'O', 'U'};
    private static final List<String> allWords = new ArrayList<>();

    public int solution(String word) {
        // 길이가 1~5인 문자 전부 생성
        for (int i = 1; i < 6; i++) {
            dfs(0, "", i);
        }

        Collections.sort(allWords);

        int answer = 0;
        int index = 1;

        for (String temp : allWords) {
            if (temp.equals(word)) {
                answer = index;
            }
            index++;
        }

        return answer;
    }

    private void dfs(int depth, String temp, int length) {
        if (depth == length) {
            allWords.add(temp);
            return;
        }

        for (int i = 0; i < 5; i++) {
            dfs(depth + 1, temp + words[i], length);
        }
    }

}
