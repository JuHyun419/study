package level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 대충_만든_자판 {

    public static int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> minPress = getMinimalPressKey(keymap);
        
        return getAnswer(minPress, targets, targets.length);
    }

    // 문자 별 최소로 눌러야하는 키 설정
    private static Map<Character, Integer> getMinimalPressKey(String[] keymap) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < keymap.length; i++) {
            String word = keymap[i];
            int index = 1;
            for (int j = 0; j < word.length(); j++) {
                map.put(word.charAt(j), Math.min(index, map.getOrDefault(word.charAt(j), index)));
                index++;
            }
        }

        return map;
    }

    private static int[] getAnswer(Map<Character, Integer> minPress, String[] targets, int length) {
        int[] answer = new int[length];
        int answerIndex = 0;
        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];
            int press = 0;
            for (int j = 0; j < target.length(); j++) {
                // 목표 문자열을 작성할 수 없을 때
                if (!minPress.containsKey(target.charAt(j))) {
                    press = -1;
                    break;
                }

                press += minPress.get(target.charAt(j));
            }
            answer[answerIndex++] = press;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] keymap = {"AAAAA", "BCEFD"};
        String[] targets = {"ABCD","AABB", "AAA"};

        System.out.println(Arrays.toString(solution(keymap, targets)));
    }

}
