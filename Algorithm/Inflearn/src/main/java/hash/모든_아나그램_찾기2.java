package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 모든_아나그램_찾기2 {

    public static void main(String[] args) {
        모든_아나그램_찾기2 T = new 모든_아나그램_찾기2();
        Scanner kb = new Scanner(System.in);
        String a = kb.next();
        String b = kb.next();
        System.out.print(T.solution(a, b));
    }

    private int solution(String a, String b) {
        Map<Character, Integer> originalMap = new HashMap<>();
        Map<Character, Integer> compareMap = new HashMap<>(); // "abc" => a=1, b=1, c=1

        // 비교 대상 문자 설정
        for (char c : b.toCharArray()) {
            compareMap.put(c, compareMap.getOrDefault(c, 0) + 1);
        }

        // 첫 Slide Window
        for (int i = 0; i < b.length(); i++) {
            originalMap.put(a.charAt(i), originalMap.getOrDefault(a.charAt(i), 0) + 1);
        }

        int left = 0;
        int count = 0;

        for (int right = b.length(); right < a.length(); right++) {
            if (isAnagram(originalMap, compareMap)) {
                count++;
            }
            char leftKey = a.charAt(left);
            char rightKey = a.charAt(right);
            originalMap.put(rightKey, originalMap.getOrDefault(rightKey, 0) + 1); // right 문자 삽입
            originalMap.put(leftKey, originalMap.get(leftKey) - 1);   // left 문자 제거

            if (originalMap.get(leftKey) == 0) originalMap.remove(leftKey);
            left++;
        }
        return (isAnagram(originalMap, compareMap)) ? count + 1 : count;
    }

    private boolean isAnagram(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        return map1.equals(map2);
    }
}
