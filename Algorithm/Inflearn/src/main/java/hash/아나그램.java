package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * HashMap 사용
 * 다른풀이 -> 하나의 문자열을 Map에 넣고, 다른 문자열을 반복하면서 기존 Map에 없거나 키가 존재하지 않을경우로 비교
 */
public class 아나그램 {

    private String solution(String a, String b) {
        Map<Character, Integer> anagram1 = new HashMap<>();
        Map<Character, Integer> anagram2 = new HashMap<>();

        for (char ch : a.toCharArray()) {
            anagram1.put(ch, anagram1.getOrDefault(ch, 0) + 1);
        }

        for (char ch : b.toCharArray()) {
            anagram2.put(ch, anagram2.getOrDefault(ch, 0) + 1);
        }

        for (Character character : anagram1.keySet()) {
            if (anagram1.get(character) != anagram2.get(character)) {
                return "NO";
            }
        }

        return "YES";
    }

    public static void main(String[] args) {
        아나그램 T = new 아나그램();
        Scanner kb = new Scanner(System.in);
        String a = kb.next();
        String b = kb.next();
        System.out.print(T.solution(a, b));
    }

}
