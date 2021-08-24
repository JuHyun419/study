package string;

import java.util.HashMap;
import java.util.Map;

// TODO:
public class Redistribute_Characters_to_Make_All_String_Equals_1897 {
    public static boolean makeEqual(String[] words) {
        // 전체 문자열 길이의 합 비교
        int arrayLength = words.length;
        int wordsSumLength = 0;

        for (String word : words) {
            wordsSumLength += word.length();
        }

        // 전체 문자열 길이의 합에서 배열 길이 나머지로 떨어지지 않을때
        // ex) words = ["ab", "a"]
        if (wordsSumLength % arrayLength != 0) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>(); // 문자별 갯수

        for (String word : words) {
            char[] ch = word.toCharArray();
            for (char c : ch) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
        }

        for (Character character : map.keySet()) {
            if (map.get(character) % arrayLength != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"a", "a", "a"};

        System.out.println(makeEqual(words));
    }
}
